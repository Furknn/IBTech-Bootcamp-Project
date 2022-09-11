package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.bootcamp.entity.Category;

public class CategoryXmlManager extends BaseXmlManager<Category> {

    private static CategoryXmlManager instance=null;
    public static CategoryXmlManager getInstance() {
        if (instance == null) {
            instance = new CategoryXmlManager();
        }
        return instance;
    }

    protected Element format(Document document, Category category) {
        Element element = document.createElement("category");
        element.setAttribute("id", String.valueOf(category.getId()));
        element.appendChild(createElement(document, "name", category.getName()));
        return element;
    }

    protected Element formatList(Document document, List<Category> list) {
        Element elements = document.createElement("categories");
        for (Category category : list) {
            elements.appendChild(format(document, category));
        }
        return elements;
    }

    @Override
    public Category parse(Document document) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        return new Category(id, name);
    }

    @Override
    public List<Category> parseList(Document document) {
        List<Category> categories = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            long id = Long.parseLong(element.getAttribute("id"));
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            categories.add(new Category(id, name));
        }

        return categories;
    }
}
