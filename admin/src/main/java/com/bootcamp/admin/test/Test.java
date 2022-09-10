package com.bootcamp.admin.test;

import com.bootcamp.entity.Category;
import com.bootcamp.manager.CategoryManager;

public class Test {
	public static void main(String[] args) {
		// get category with id=12
		CategoryManager categoryManager = new CategoryManager();
		Category category = categoryManager.getById(12);
		System.out.println(category.getName());
	}
}
