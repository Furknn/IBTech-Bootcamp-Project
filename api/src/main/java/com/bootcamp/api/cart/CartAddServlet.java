package com.bootcamp.api.cart;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.Cart;
import com.bootcamp.entity.CartProduct;
import com.bootcamp.manager.CartManager;
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
		if (cartProduct == null) {
			response.getWriter().append("CartProduct is null");
			response.setStatus(400);
			return;
		}

		Cart cart = CartManager.getInstance().getById(cartProduct.getCartId());
		if (cart == null) {
			response.getWriter().append("Cart not found");
			response.setStatus(500);
			return;
		}

		CartProduct existingCartProduct = CartProductManager.getInstance().getByCartAndProductId(cart.getId(), cartProduct.getProductId());
		if (existingCartProduct != null) {
			existingCartProduct.setQuantity(cartProduct.getQuantity());
			existingCartProduct.setLineAmount(cartProduct.getLineAmount());
			cartProduct = CartProductManager.getInstance().update(existingCartProduct);
		} else {
			cartProduct = CartProductManager.getInstance().create(cartProduct);
		}

		if (cartProduct == null){
			response.getWriter().append("CartProduct not created");
			response.setStatus(500);
			return;
		}

		double totalAmount = 0.0;
		List<CartProduct> cartProducts = CartProductManager.getInstance().getByCartId(cartProduct.getCartId());
		for (CartProduct cp:cartProducts) {
			totalAmount += cp.getLineAmount();
		}
		cart.setTotalAmount(totalAmount);
		CartManager.getInstance().update(cart);

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
