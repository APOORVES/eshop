package com.eshop.cart.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.eshop.cart.entity.Cart;
import com.eshop.cart.rest.CartService;
import com.eshop.cart.rest.SaleNotificationService;



@Service
public class SaleNotificationServiceImpl implements SaleNotificationService
{
	  private static final Logger LOGGER = LoggerFactory.getLogger(SaleNotificationServiceImpl.class);
	  public final String CART_SOLD = "CARTSOLD";
	  
	  @Autowired
	  private CartService cartService;

		@Autowired
		private KafkaTemplate<String, String> kafkaTemplate;
		
		public final String SALE_NOTIFICATION_PRODUCT_TOPIC = "SALE_NOTIFICATION_PRODUCT_TOPIC";

		public final String SALE_NOTIFICATION_CART_TOPIC = "SALE_NOTIFICATION_CART_TOPIC";
		
	  @Override
	  public void processSale(String cartId) 
	  {
	    LOGGER.info("Processing Sale for cartId={}", cartId);
	    Cart cartSold = cartService.getCart(cartId);
	    cartSold.setCartStatus(CART_SOLD);
	    cartService.updateCart(cartSold);
	    cartSold.getProductsInCart().forEach((k,v)->
		{
			send(k + "_" +v);
		});
		    LOGGER.info("Processed Sale for the cartId={}", cartId);
	  	}

	  @KafkaListener(topics = "SALE_NOTIFICATION_CART_TOPIC")
	  public void listenForSale(String cartId) 
	  {
	    LOGGER.info("Sale received for cart={}", cartId);
	    processSale(cartId);
	  }
		
		public void send(String prodInfo) 
		{
		    kafkaTemplate.send(SALE_NOTIFICATION_PRODUCT_TOPIC, prodInfo);
		}

}
