package com.bootcamp.portal;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.Cart;
import com.bootcamp.utils.WebHelper;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CartXmlManager;

public class CartClient {
    private static final String BASE_URL = "http://localhost:8080/api/cart";

    public static Cart createCart() {
        return createCart(null);
    }

    public static Cart createCart(String name) {
        String url;
        if (name == null) {
            url = BASE_URL + "/create";
        } else {
            url = BASE_URL + "/create?name=" + name;
        }

        InputStream response = WebHelper.get(url);
        try {
            Document document = XmlHelper.parse(response);
            Cart cart = CartXmlManager.getInstance().parse(document);
            return cart;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }
}
