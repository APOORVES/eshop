package com.eshop.payment.entity;

public class PaymentRequest 
{
	private String creditCard;
	private String cartId;
	public String getCreditCard() 
	{
		return creditCard;
	}
	public void setCreditCard(String creditCard) 
	{
		this.creditCard = creditCard;
	}
	public String getCartId() 
	{
		return cartId;
	}
	public void setCartId(String cartId) 
	{
		this.cartId = cartId;
	}
	
	

}
