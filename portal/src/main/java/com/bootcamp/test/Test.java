package com.bootcamp.test;

import java.util.List;

import com.bootcamp.entity.Category;
import com.bootcamp.entity.Product;
import com.bootcamp.portal.CategoryClient;
import com.bootcamp.portal.ProductClient;

public class Test {
	public static void main(String[] args) {
		// get all categories
		List<Category> categories = CategoryClient.getAll();
		for (Category category : categories) {
			System.out.println(category.getName());

			// get products with category id
			List<Product> products = ProductClient.getByCategoryId(category.getId());
			for (Product product : products) {
				System.out.println(product.getName());
			}

		}
	}
}
