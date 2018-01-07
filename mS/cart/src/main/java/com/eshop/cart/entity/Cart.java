package com.eshop.cart.entity;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")
public class Cart 
{

	@Id
	private String cartId;
	private HashMap<String, Long> productsInCart;
	private String cartStatus;
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public HashMap<String, Long> getProductsInCart() {
		return productsInCart;
	}
	public void setProductsInCart(HashMap<String, Long> productsInCart) {
		this.productsInCart = productsInCart;
	}
	public String getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	} 
	
	

	
}
