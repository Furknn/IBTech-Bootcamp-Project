package com.bootcamp.api.test;

import java.util.List;

import com.bootcamp.entity.Cart;
import com.bootcamp.entity.CartProduct;
import com.bootcamp.manager.CartManager;
import com.bootcamp.manager.CartProductManager;

public class Test {
	public static void main(String[] args) {
		// get cart products
		List<CartProduct> cpm= CartProductManager.getInstance().getAll();
		// print cart products
		for (CartProduct cartProduct : cpm) {
			System.out.println(cartProduct.getId());
			System.out.println(cartProduct.getCartId());
			System.out.println(cartProduct.getProductId());
			System.out.println(cartProduct.getQuantity());
			System.out.println(cartProduct.getPrice());
			System.out.println(cartProduct.getTaxRate());
			System.out.println(cartProduct.getLineAmount());
			System.out.println("---------------------");
		}

		System.out.println("done");

		List<Cart> carts = CartManager.getInstance().getAll();
		// print carts
		for (Cart cart : carts) {
			System.out.println(cart.getId());
			System.out.println(cart.getCustomerName());
			System.out.println("---------------------");
		}


	}
}
