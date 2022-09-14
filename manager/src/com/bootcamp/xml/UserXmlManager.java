package com.bootcamp.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootcamp.entity.User;

public class UserXmlManager extends BaseXmlManager<User> {

    private static UserXmlManager instance = null;

    public static UserXmlManager getInstance() {
        if (instance == null) {
            instance = new UserXmlManager();
        }
        return instance;
    }

    @Override
    protected Node format(Document document, User t) {
        Element element = document.createElement("user");
        element.setAttribute("id", String.valueOf(t.getId()));
        element.appendChild(createElement(document, "username", t.getUsername()));
        element.appendChild(createElement(document, "password", t.getPassword()));
        return element;
    }

    @Override
    public User parse(Document document) {
        Element element = document.getDocumentElement();
        long id = Long.parseLong(element.getAttribute("id"));
        String username = element.getElementsByTagName("username").item(0).getTextContent();
        String password = element.getElementsByTagName("password").item(0).getTextContent();
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
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            long id = Long.parseLong(element.getAttribute("id"));
            String username = element.getElementsByTagName("username").item(0).getTextContent();
            String password = element.getElementsByTagName("password").item(0).getTextContent();
            users.add(new User(id, username, password));
        }

        return users;
    }
}
