package com.bootcamp.api.product;

import java.io.IOException;

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

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long productId = Long.parseLong(request.getParameter("productid"));
		ProductManager pm = ProductManager.getInstance();
		Product product = pm.getById(productId);
		ProductXmlManager productXmlManager = ProductXmlManager.getInstance();
		Document document = productXmlManager.format(product);
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
