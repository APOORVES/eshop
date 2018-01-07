package com.eshop.cart.rest;

import java.util.List;

import com.eshop.cart.entity.Cart;

public interface CartService 
{

	public Cart getCart(String id);
	public List<Cart> getCartAll();
	public Cart updateCart(Cart cart);
	public Cart addCart(Cart cart);
	public boolean deleteCart(String cartId);

}
