package com.bootcamp.xml;

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

    public Category parse(Document document) {
        Element element = document.getDocumentElement();
        long id=Long.parseLong(element.getAttribute("id"));
        String name=element.getElementsByTagName("name").item(0).getTextContent();
        return new Category(id, name);
    }

}
