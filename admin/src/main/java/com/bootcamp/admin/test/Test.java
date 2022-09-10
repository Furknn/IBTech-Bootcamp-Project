package com.bootcamp.admin.test;

import com.bootcamp.manager.ProductManager;

public class Test {
	public static void main(String[] args) {
		// delete product with id = 9
		ProductManager productManager = new ProductManager();
		productManager.delete(9);

	}
}
