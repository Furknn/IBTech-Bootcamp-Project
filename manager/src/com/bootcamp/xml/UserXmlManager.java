package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bootcamp.entity.User;

public class UserXmlManager extends BaseXmlManager<User> {

    @Override
    protected Node format(Document document, User t) {
        Element element = document.createElement("user");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "username", t.getUsername()));
        element.appendChild(createElement(document, "password", t.getPassword()));
        return element;
    }

    @Override
    protected User parse(Document document, int index) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String username = element.getElementsByTagName("username").item(index).getTextContent();
        String password = element.getElementsByTagName("password").item(index).getTextContent();
        return new User(id, username, password);
    }

    @Override
    protected Element formatList(Document document, List<User> list) {
        Element elements = document.createElement("users");
        for (User user : list) {
            elements.appendChild(format(document, user));
        }
        return elements;
    }

    @Override
    public List<User> parseList(Document document) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < document.getElementsByTagName("user").getLength(); i++) {
            users.add(parse(document, i));
        }
        return users;
    }
}
