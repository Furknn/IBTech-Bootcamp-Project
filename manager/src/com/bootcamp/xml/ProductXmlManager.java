package com.bootcamp.xml;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootcamp.entity.Product;

public class ProductXmlManager extends BaseXmlManager<Product> {

    private static ProductXmlManager instance=null;
    public static ProductXmlManager getInstance() {
        if (instance == null) {
            instance = new ProductXmlManager();
        }
        return instance;
    }

    @Override
    protected Node format(Document document, Product t) {
        Element element = document.createElement("product");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "name", t.getName()));
        element.appendChild(createElement(document, "price", String.valueOf(t.getPrice())));
        element.appendChild(createElement(document, "categoryid", String.valueOf(t.getCategoryId())));
        element.appendChild(createElement(document, "imageUrl", t.getImageUrl()));
        return element;
    }

    @Override
    public Product parse(Document document) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
        long categoryId = Long.parseLong(element.getElementsByTagName("categoryid").item(0).getTextContent());
        String imageUrl = element.getElementsByTagName("imageurl").item(0).getTextContent();
        return new Product(id, name, price, categoryId, imageUrl);
    }

    @Override
    protected Element formatList(Document document, List<Product> list) {
        Element elements = document.createElement("products");
        for (Product product : list) {
            elements.appendChild(format(document, product));
        }
        return elements;
    }

    @Override
    public List<Product> parseList(Document document) {
        List<Product> products = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            long id = Long.parseLong(element.getAttribute("id"));
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
            long categoryId = Long.parseLong(element.getElementsByTagName("categoryid").item(0).getTextContent());
            Node image= element.getElementsByTagName("imageurl").item(0);
            String imageUrl = image==null?"":image.getTextContent();
            products.add(new Product(id, name, price, categoryId, imageUrl));
        }

        return products;
    }
}
