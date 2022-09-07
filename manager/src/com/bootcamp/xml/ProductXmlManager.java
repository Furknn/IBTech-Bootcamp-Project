package com.bootcamp.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bootcamp.entity.Product;

public class ProductXmlManager extends BaseXmlManager<Product>{

    @Override
    protected Node format(Document document, Product t) {
        Element element = document.createElement("product");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "name", t.getName()));
        element.appendChild(createElement(document, "price", String.valueOf(t.getPrice())));
        element.appendChild(createElement(document, "categoryid", String.valueOf(t.getCategoryId())));
        return element;
    }

    @Override
    public Product parse(Document document) {
        Element element = document.getDocumentElement();
        long id=Long.parseLong(element.getAttribute("id"));
        String name=element.getElementsByTagName("name").item(0).getTextContent();
        double price=Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
        long categoryId=Long.parseLong(element.getElementsByTagName("categoryid").item(0).getTextContent());
        return new Product(id, name, price, categoryId);
    }
}
