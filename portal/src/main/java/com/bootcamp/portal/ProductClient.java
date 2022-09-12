package com.bootcamp.portal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.Product;
import com.bootcamp.utils.WebHelper;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.ProductXmlManager;

public class ProductClient {
    private static final String productsUrl = "http://localhost:8080/api/products";
    private static final String productUrl = "http://localhost:8080/api/product";
    
    public static List<Product> getByCategoryId(long categoryId) {
        String url = productsUrl + "?categoryid=" + categoryId;
        InputStream in = WebHelper.get(url);
        Document doc;
        try {
            doc = XmlHelper.parse(in);
            ProductXmlManager pxm =  ProductXmlManager.getInstance();
            return pxm.parseList(doc);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product getById(long id) {
        String url = productUrl + "?productid=" + id;
        InputStream in = WebHelper.get(url);
        Document doc;
        try {
            doc = XmlHelper.parse(in);
            ProductXmlManager pxm = ProductXmlManager.getInstance();
            return pxm.parse(doc);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }
}
