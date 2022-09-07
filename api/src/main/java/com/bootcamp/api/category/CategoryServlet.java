package com.bootcamp.api.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootcamp.entity.Category;
import com.bootcamp.manager.CategoryManager;


@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CategoryManager cm= new CategoryManager();
		 List<Category> categories= cm.getAll();
		 PrintWriter resp=response.getWriter();
		 for (Category cat: categories) {
			 resp.append(cat.Name);
		 }		
	}
}
