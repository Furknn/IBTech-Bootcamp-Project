package com.bootcamp.portal;

import java.io.IOException;
import java.net.URLConnection;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bootcamp.entity.User;
import com.bootcamp.utils.WebHelper;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.UserXmlManager;

public class UserClient {
    private static final String BASE_URL = "http://localhost:8080/api/user";

    public static boolean createUser(User user) {
        String url = BASE_URL + "/create";
        Document document = UserXmlManager.getInstance().format(user);
        URLConnection connection = WebHelper.connect(url);
        try {
            connection.setDoOutput(true);
			XmlHelper.dump(document, connection.getOutputStream());
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
			return false;
		}

        try {
            connection.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUser(User user) {
        String url = BASE_URL + "/check";
        Document document = UserXmlManager.getInstance().format(user);
        URLConnection connection = WebHelper.connect(url);
        try {
            connection.setDoOutput(true);
            XmlHelper.dump(document, connection.getOutputStream());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            connection.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
