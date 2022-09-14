package com.bootcamp.api.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootcamp.manager.CartManager;
import com.bootcamp.manager.CartProductManager;


@WebServlet("/cart/remove")
public class CartRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cartId = Long.parseLong(request.getParameter("cartproductid"));
		CartProductManager.getInstance().delete(cartId);

		if (CartManager.getInstance().getById(cartId) == null) {
			response.getWriter().append("Cart product removed");
			response.setStatus(200);
		} else {
			response.getWriter().append("Cart product not removed");
			response.setStatus(500);
		}
	}

}
