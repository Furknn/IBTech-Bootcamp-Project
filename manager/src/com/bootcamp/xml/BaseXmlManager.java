package com.bootcamp.xml;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.bootcamp.utils.XmlHelper;

public abstract class BaseXmlManager<T> {

    public Document format(T t) {
        Document document;
        try {
            document = XmlHelper.createDocument();
            document.appendChild(format(document, t));
            return document;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract Node format(Document document, T t);

    protected Node createElement(Document document, String name, String value) {
        Element element = document.createElement(name);
        element.setTextContent(value);
        return element;
    }

    public abstract T parse(Document document);

}
