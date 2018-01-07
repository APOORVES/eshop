package com.eshop.product.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.eshop.product.entity.Product;
import com.eshop.product.rest.ProductService;
import com.eshop.product.rest.SaleNotificationService;



@Service
public class SaleNotificationServiceImpl implements SaleNotificationService
{
	  private static final Logger LOGGER = LoggerFactory.getLogger(SaleNotificationServiceImpl.class);
	  public final String CART_SOLD = "CARTSOLD";
	  
	  @Autowired
	  private ProductService productService;

	  public final String SALE_NOTIFICATION_PRODUCT_TOPIC = "SALE_NOTIFICATION_PRODUCT_TOPIC";
		
	  @Override
	  public void processSale(String prodInfo) 
	  {
	    LOGGER.info("Processing Sale for productId={}", prodInfo);
	    if(prodInfo!=null)
	    {
	    	String[] productInfo = prodInfo.split("_");
	    	String productId = productInfo[0];
	    	long productQuantitySold = Long.parseLong(productInfo[1]);
		    Product productSold = productService.getProduct(productId);
		    if(productSold != null)
		    {
		    	productSold.setQuantityInStock(productSold.getQuantityInStock()-productQuantitySold);
		    	productService.updateProduct(productSold);
		    }
	    }
	    LOGGER.info("Processing Sale for the productId={}", prodInfo);
	  }

	  @KafkaListener(topics = "SALE_NOTIFICATION_PRODUCT_TOPIC")
	  public void listenForSale(String prodInfo) 
	  {
	    LOGGER.info("Sale received for product={}", prodInfo);
	    processSale(prodInfo);
	  }

}
