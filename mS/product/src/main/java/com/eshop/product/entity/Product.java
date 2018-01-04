package com.eshop.product.entity;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product 
{

	@Id
	private long productId;
	private String description;
	private long quantityInStock;
	private HashMap<String, String> attributes;
	private double price;
	
	
	
	public long getProductId() 
	{
		return productId;
	}
	public void setProductId(long productId) 
	{
		this.productId = productId;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public long getQuantityInStock() 
	{
		return quantityInStock;
	}
	public void setQuantityInStock(long quantityInStock) 
	{
		this.quantityInStock = quantityInStock;
	}
	public HashMap<String, String> getAttributes() 
	{
		return attributes;
	}
	public void setAttributes(HashMap<String, String> attributes) 
	{
		this.attributes = attributes;
	}
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	
}
