package com.bootcamp.api.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.CartProduct;
import com.bootcamp.manager.CartProductManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CartProductXmlManager;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (IOException | ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
			response.setStatus(500);
			return;
		}
		CartProduct cartProduct = CartProductXmlManager.getInstance().parse(document);
		cartProduct = CartProductManager.getInstance().create(cartProduct);
		document = CartProductXmlManager.getInstance().format(cartProduct);
		response.setContentType("application/xml; charset=UTF-8");
		try {
			XmlHelper.dump(document, response.getOutputStream());
			response.setStatus(200);
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

}
