package com.eshop.payment.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshop.payment.entity.PaymentRequest;
import com.eshop.payment.rest.PaymentService;
import com.eshop.payment.rest.SaleNotificationService;

@Path("/payment")
@Component
public class PaymentServiceImpl implements PaymentService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Value("${validCreditCards}")
	private String validCreditCards;
	
	@Autowired
	private SaleNotificationService saleNotificationService;
	
	@Path("/makePayment")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	public boolean makePayment(PaymentRequest paymentRequest) 
	{
		boolean returnValue = false;
	    LOGGER.debug("/payment/makePayment called");
	    if(paymentRequest != null && paymentRequest.getCreditCard() != null && this.validCreditCards != null && this.validCreditCards.contains(paymentRequest.getCreditCard()))
	    {
	    	saleNotificationService.send(paymentRequest.getCartId());
	    	returnValue = true;
	    }
	    return returnValue;
	}

	

}
