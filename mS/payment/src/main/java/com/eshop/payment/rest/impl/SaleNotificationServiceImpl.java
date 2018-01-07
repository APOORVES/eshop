package com.eshop.payment.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.eshop.payment.rest.SaleNotificationService;



@Service
public class SaleNotificationServiceImpl implements SaleNotificationService
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public final String SALE_NOTIFICATION_CART_TOPIC = "SALE_NOTIFICATION_CART_TOPIC";
	
	public void send(String message) 
	{
	    kafkaTemplate.send(SALE_NOTIFICATION_CART_TOPIC, message);
	}

}
