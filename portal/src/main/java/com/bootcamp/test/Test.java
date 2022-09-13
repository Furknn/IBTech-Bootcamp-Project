package com.bootcamp.test;

import java.util.List;

import com.bootcamp.entity.Product;
import com.bootcamp.portal.ProductClient;

public class Test {
	public static void main(String[] args) {
		// get products of category id = 12
		List<Product> products = ProductClient.getByCategoryId(12);
		// print products
		for (Product product : products) {
			System.out.println(product.getName());
			System.out.println(product.getDetail());
		}


	}
}
