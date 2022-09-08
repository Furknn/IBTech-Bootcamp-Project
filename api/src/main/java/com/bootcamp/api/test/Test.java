package com.bootcamp.api.test;

import java.util.List;

import com.bootcamp.entity.Category;
import com.bootcamp.entity.Product;
import com.bootcamp.manager.CategoryManager;
import com.bootcamp.manager.ProductManager;

public class Test {
	public static void main(String[] args) {
		// insert 10 products for each categories with id 12, 13, 14
		CategoryManager cm = new CategoryManager();
		ProductManager pm=new ProductManager();
		List<Category> categories = cm.getAll();
		for (Category category : categories) {
			
			for (int i = 0; i < 10; i++) {
				Product product = new Product();
			product.setCategoryId(category.getId());
			product.setName("Product " + i  +category.getName());
			product.setPrice(1000);
			
				pm.create(product);
			}

		}
	}
}
