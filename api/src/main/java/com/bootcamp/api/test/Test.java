package com.bootcamp.api.test;

import java.util.List;

import org.w3c.dom.Document;

import com.bootcamp.entity.Category;
import com.bootcamp.manager.CategoryManager;
import com.bootcamp.xml.CategoryXmlManager;

public class Test {
	 public static void main(String[] args) {
	        // get all categories
	        CategoryManager categoryManager = new CategoryManager();
	        List<Category> categories = categoryManager.getAll();
	        // format categories to xml
	        CategoryXmlManager categoryXmlManager = new CategoryXmlManager();
	        Document document = categoryXmlManager.formatList(categories);
	        
	    }
}
