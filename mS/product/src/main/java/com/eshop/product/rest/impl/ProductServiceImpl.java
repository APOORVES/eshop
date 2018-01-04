package com.eshop.product.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.eshop.product.entity.Product;
import com.eshop.product.repository.ProductRepository;
import com.eshop.product.rest.ProductService;

@Path("/product")
@Component
public class ProductServiceImpl implements ProductService 
{
	@Autowired
	ProductRepository productRepository;

	@Path("/get")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public Product getProduct(@FormParam("productId") long id) 
	{
		return productRepository.findOne(id);
	}

	@Path("/update")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public boolean updateProduct(Product product) 
	{
		boolean returnValue = false;
		Product returnedProduct = productRepository.save(product);
		if(returnedProduct != null)
			returnValue = true;
		return returnValue;
	}

	@Path("/add")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public boolean addProduct(Product product) 
	{
		boolean returnValue = false;
		Product returnedProduct = productRepository.save(product);
		if(returnedProduct != null)
			returnValue = true;
		return returnValue;
	}

	@Path("/delete")
	@POST
	@Override
	public boolean deleteProduct(@FormParam("productId") long productId) 
	{
		boolean returnValue = false;
		try
		{
			productRepository.delete(productId);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			returnValue = false;
		}
		returnValue = true;
		return returnValue;
	}
	

}
