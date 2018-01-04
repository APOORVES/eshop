package com.eshop.product.rest;

import com.eshop.product.entity.Product;

public interface ProductService 
{

	public Product getProduct(long id);
	public boolean updateProduct(Product product);
	public boolean addProduct(Product product);
	public boolean deleteProduct(long productId);

}
