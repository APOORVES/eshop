package com.eshop.product.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Path("/get")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public Product getProduct(@FormParam("productId") long productId) 
	{
	    LOGGER.debug("/product/get called with id = {}", productId);		
	    return productRepository.findOne(productId);
	}

	@Path("/update")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public boolean updateProduct(Product product) 
	{
	    LOGGER.debug("/product/update called with product = {}", product);		
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
	    LOGGER.debug("/product/add called with product = {}", product);		
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
	    LOGGER.debug("/product/add called with delete = {}", productId);		
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
