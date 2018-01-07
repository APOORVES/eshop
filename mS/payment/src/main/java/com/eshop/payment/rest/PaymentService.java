package com.eshop.payment.rest;

import com.eshop.payment.entity.PaymentRequest;

public interface PaymentService 
{

	public boolean makePayment(PaymentRequest paymentRequest);

}
