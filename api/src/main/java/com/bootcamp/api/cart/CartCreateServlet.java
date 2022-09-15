package com.bootcamp.api.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bootcamp.entity.Cart;
import com.bootcamp.manager.CartManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CartXmlManager;

@WebServlet("/cart/create")
public class CartCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if(name==null)
			name="nameless";
		Cart cart = new Cart();
		cart.setCustomerName(name);
		cart.setTotalAmount(0.0);
		cart = CartManager.getInstance().create(cart);

		CartXmlManager cartXmlManager = CartXmlManager.getInstance();
		Document document = cartXmlManager.format(cart);
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
