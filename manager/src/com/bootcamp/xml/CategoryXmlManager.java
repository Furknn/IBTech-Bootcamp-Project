package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.bootcamp.entity.Category;

public class CategoryXmlManager extends BaseXmlManager<Category> {

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
    protected Category parse(Document document, int index) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String name = element.getElementsByTagName("name").item(index).getTextContent();
        return new Category(id, name);
    }

    @Override
    public List<Category> parseList(Document document) {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < document.getElementsByTagName("category").getLength(); i++) {
            categories.add(parse(document, i));
        }
        return categories;
    }
}
