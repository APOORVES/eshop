package com.eshop.cart.rest.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.cart.entity.Cart;
import com.eshop.cart.repository.CartRepository;
import com.eshop.cart.rest.CartIdGenerator;
import com.eshop.cart.rest.CartService;

@Path("/cart")
@Component
public class CartServiceImpl implements CartService 
{
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartIdGenerator cartIdGenerator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
	
	@GET
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public Cart getCart(@FormParam("cartId") String cartId) 
	{
	    LOGGER.debug("/cart/get called with id = {}", cartId);		
	    return cartRepository.findOne(cartId);
	}


	@Path("/getAll")
	@GET
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public List<Cart> getCartAll() 
	{
	    LOGGER.debug("/cart/getAll called");		
	    return cartRepository.findAll();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public Cart updateCart(Cart cart) 
	{
	    LOGGER.debug("/cart/update called with cart = {}", cart);		
	    if(cart!=null && cart.getCartId() == null)
	    {
	    	cart.setCartStatus("INPROGRESS");
	    }
		Cart returnedCart = cartRepository.save(cart);
		return returnedCart;
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public Cart addCart(Cart cart) 
	{
	    LOGGER.debug("/cart/add called with cart = {}", cart);
	    if(cart!=null && cart.getCartId() == null)
	    {
	    	cart.setCartStatus("INPROGRESS");
	    }
		Cart returnedCart = cartRepository.save(cart);
		return returnedCart;
	}

	@DELETE
	@Override
	public boolean deleteCart(@FormParam("cartId") String cartId) 
	{
	    LOGGER.debug("/cart/add called with delete = {}", cartId);		
		boolean returnValue = false;
		try
		{
			cartRepository.delete(cartId);
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
