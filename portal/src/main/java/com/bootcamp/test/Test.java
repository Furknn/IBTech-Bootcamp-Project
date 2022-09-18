package com.bootcamp.test;

import java.util.List;

import com.bootcamp.entity.Cart;
import com.bootcamp.entity.CartProduct;
import com.bootcamp.portal.CartClient;
import com.bootcamp.portal.CartProductClient;

public class Test {
	public static void main(String[] args) {
		// create cart
		Cart cart = CartClient.createCart();
		System.out.println("cart created: " + cart.getId());

		// add cart
		CartProduct cartProduct = new CartProduct();
		cartProduct.setCartId(cart.getId());
		cartProduct.setProductId(34);
		cartProduct.setQuantity(1);
		cartProduct.setLineAmount(23);
		cartProduct.setTaxRate(15);
		cartProduct.setPrice(23);
		cartProduct = CartProductClient.addCart(cartProduct);
		System.out.println("cart added " + cartProduct.getId());
		cartProduct = CartProductClient.addCart(cartProduct);
		System.out.println("cart added " + cartProduct.getId());

		// get cart
		List<CartProduct> cartProducts = CartProductClient.getCartProducts(cart.getId());
		for (CartProduct cp : cartProducts) {
			System.out.println("cart product: " + cp.getId());
		}

		cartProducts= CartProductClient.getCartProducts(cart.getId());
		for (CartProduct cp2 : cartProducts) {
			System.out.println("cart product: " + cp2.getId());
		}


	}
}
