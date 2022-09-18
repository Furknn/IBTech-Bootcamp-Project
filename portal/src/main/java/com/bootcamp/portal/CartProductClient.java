package com.bootcamp.portal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.CartProduct;
import com.bootcamp.utils.WebHelper;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CartProductXmlManager;

public class CartProductClient {
    private static final String BASE_URL = "http://localhost:8080/api/cart";

    public static CartProduct addCart(CartProduct cartProduct) {
        String url = BASE_URL + "/add";
        Document document = CartProductXmlManager.getInstance().format(cartProduct);
        URLConnection connection = WebHelper.connect(url);
        try {
            connection.setDoOutput(true);
            XmlHelper.dump(document, connection.getOutputStream());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            InputStream iStream = connection.getInputStream();
            Document response = XmlHelper.parse(iStream);
            return CartProductXmlManager.getInstance().parse(response);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean cartRemove(long cartProductId){
        String url = BASE_URL + "/remove?cartproductid="+cartProductId;
        URLConnection connection = WebHelper.connect(url);
        try {
            connection.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<CartProduct> getCartProducts(long cartId){
        String url = BASE_URL + "/view?cartid="+cartId;
        URLConnection connection = WebHelper.connect(url);
        try {
            Document response = XmlHelper.parse(connection.getInputStream());
            return CartProductXmlManager.getInstance().parseList(response);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }
}
