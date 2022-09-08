package com.bootcamp.api.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bootcamp.entity.Category;
import com.bootcamp.manager.CategoryManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CategoryXmlManager;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryManager cm = new CategoryManager();
		List<Category> categories = cm.getAll();

		CategoryXmlManager categoryXmlManager = new CategoryXmlManager();
		Document document = categoryXmlManager.formatList(categories);
		response.setContentType("application/xml; charset=UTF-8");
		try {
			XmlHelper.dump(document, response.getOutputStream());
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}

	}
}
