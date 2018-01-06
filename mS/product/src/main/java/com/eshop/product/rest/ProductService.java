package com.eshop.product.rest;

import java.util.List;

import com.eshop.product.entity.Product;

public interface ProductService 
{

	public Product getProduct(String id);
	public boolean updateProduct(Product product);
	public Product addProduct(Product product);
	public boolean deleteProduct(String productId);
	public List<Product> getProductsAll();

}
