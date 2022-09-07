package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bootcamp.entity.Product;

public class ProductXmlManager extends BaseXmlManager<Product> {

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
    protected Product parse(Document document, int index) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String name = element.getElementsByTagName("name").item(index).getTextContent();
        double price = Double.parseDouble(element.getElementsByTagName("price").item(index).getTextContent());
        long categoryId = Long.parseLong(element.getElementsByTagName("categoryid").item(index).getTextContent());
        return new Product(id, name, price, categoryId);
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
        for (int i = 0; i < document.getElementsByTagName("product").getLength(); i++) {
            products.add(parse(document, i));
        }
        return products;
    }

}
