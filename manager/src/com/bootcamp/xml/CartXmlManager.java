package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootcamp.entity.Cart;

public class CartXmlManager extends BaseXmlManager<Cart> {
    private static CartXmlManager instance = null;

    public static CartXmlManager getInstance() {
        if (instance == null) {
            instance = new CartXmlManager();
        }
        return instance;
    }

    @Override
    protected Node format(Document document, Cart t) {
        Element element = document.createElement("cart");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "customername", t.getCustomerName()));
        element.appendChild(createElement(document, "totalamount", String.valueOf(t.getTotalAmount())));
        return element;
    }

    @Override
    public Cart parse(Document document) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String customerName = element.getElementsByTagName("customername").item(0).getTextContent();
        double totalAmount = Double.parseDouble(element.getElementsByTagName("totalamount").item(0).getTextContent());
        return new Cart(id, totalAmount, customerName);
    }

    @Override
    protected Element formatList(Document document, List<Cart> list) {
        Element elements = document.createElement("carts");
        for (Cart cart : list) {
            elements.appendChild(format(document, cart));
        }
        return elements;
    }

    @Override
    public List<Cart> parseList(Document document) {
        List<Cart> carts = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            long id = Long.parseLong(element.getAttribute("id"));
            String customerName = element.getElementsByTagName("customername").item(0).getTextContent();
            double totalAmount = Double
                    .parseDouble(element.getElementsByTagName("totalamount").item(0).getTextContent());
            carts.add(new Cart(id, totalAmount, customerName));
        }

        return carts;
    }
}
