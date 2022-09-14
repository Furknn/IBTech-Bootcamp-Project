package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootcamp.entity.CartProduct;

public class CartProductXmlManager extends BaseXmlManager<CartProduct> {
    private static CartProductXmlManager instance = null;

    public static CartProductXmlManager getInstance() {
        if (instance == null) {
            instance = new CartProductXmlManager();
        }
        return instance;
    }

    @Override
    protected Node format(Document document, CartProduct t) {
        Element element = document.createElement("cartProduct");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "cartid", String.valueOf(t.getCartId())));
        element.appendChild(createElement(document, "productid", String.valueOf(t.getProductId())));
        element.appendChild(createElement(document, "quantity", String.valueOf(t.getQuantity())));
        element.appendChild(createElement(document, "price", String.valueOf(t.getPrice())));
        element.appendChild(createElement(document, "taxrate", String.valueOf(t.getTaxRate())));
        element.appendChild(createElement(document, "lineamount", String.valueOf(t.getLineAmount())));
        return element;
    }

    @Override
    public CartProduct parse(Document document) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        long cartId = Long.parseLong(element.getElementsByTagName("cartid").item(0).getTextContent());
        long productId = Long.parseLong(element.getElementsByTagName("productid").item(0).getTextContent());
        int quantity = Integer.parseInt(element.getElementsByTagName("quantity").item(0).getTextContent());
        double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
        double taxRate = Double.parseDouble(element.getElementsByTagName("taxrate").item(0).getTextContent());
        double lineAmount = Double.parseDouble(element.getElementsByTagName("lineamount").item(0).getTextContent());
        return new CartProduct(id, cartId, productId, quantity, price, taxRate, lineAmount);
    }

    @Override
    protected Element formatList(Document document, List<CartProduct> list) {
        Element elements = document.createElement("cartProducts");
        for (CartProduct cartProduct : list) {
            elements.appendChild(format(document, cartProduct));
        }
        return elements;
    }

    @Override
    public List<CartProduct> parseList(Document document) {
        List<CartProduct> cartProducts = new ArrayList<CartProduct>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node = (Element) nodeList.item(i);
            long id = Long.parseLong(node.getAttribute("id"));
            long cartId = Long.parseLong(node.getElementsByTagName("cartId").item(0).getTextContent());
            long productId = Long.parseLong(node.getElementsByTagName("productid").item(0).getTextContent());
            int quantity = Integer.parseInt(node.getElementsByTagName("quantity").item(0).getTextContent());
            double price = Double.parseDouble(node.getElementsByTagName("price").item(0).getTextContent());
            double taxRate = Double.parseDouble(node.getElementsByTagName("taxrate").item(0).getTextContent());
            double lineAmount = Double.parseDouble(node.getElementsByTagName("lineamount").item(0).getTextContent());
            cartProducts.add(new CartProduct(id, cartId, productId, quantity, price, taxRate, lineAmount));
        }

        return cartProducts;
    }
}
