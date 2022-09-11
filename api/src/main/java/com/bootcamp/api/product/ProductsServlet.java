package com.bootcamp.api.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bootcamp.entity.Product;
import com.bootcamp.manager.ProductManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.ProductXmlManager;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long categoryId=Long.parseLong(request.getParameter("categoryid"));
		ProductManager pm = ProductManager.getInstance();
		List<Product> products = pm.getByCategory(categoryId);
		ProductXmlManager productXmlManager =ProductXmlManager.getInstance();
		Document document = productXmlManager.formatList(products);
		response.setContentType("application/xml; charset=UTF-8");
		try {
			XmlHelper.dump(document, response.getOutputStream());
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}

	}
}
