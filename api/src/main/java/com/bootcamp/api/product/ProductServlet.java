package com.bootcamp.api.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootcamp.entity.Product;
import com.bootcamp.manager.ProductManager;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManager pm=new ProductManager();
		List<Product> products=pm.getAll(); 
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(products.get(0).Name);
	}
}
