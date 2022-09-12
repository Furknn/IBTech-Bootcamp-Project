package com.bootcamp.portal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.Category;
import com.bootcamp.utils.WebHelper;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CategoryXmlManager;

public class CategoryClient {
    private static final String url ="http://localhost:8080/api/categories";

    public static List<Category> getAll() {
        InputStream in = WebHelper.get(url);
        try {
            Document doc = XmlHelper.parse(in);
            CategoryXmlManager cxm= CategoryXmlManager.getInstance();
            return cxm.parseList(doc) ;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }

    }

}
