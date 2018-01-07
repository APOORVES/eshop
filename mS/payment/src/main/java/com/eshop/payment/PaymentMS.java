package com.eshop.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = "com.eshop.Payment")

@SpringBootApplication
public class PaymentMS 
{

	public static void main(String[] args) 
	{
		//SpringApplication.run(PaymentConfig.class, args);
		SpringApplication.run(PaymentMS.class, args);

/*	    SpringApplication app = new SpringApplication(PaymentMS.class);
	    app.setWebEnvironment(false);
	    ConfigurableApplicationContext ctx = app.run(args);		*/
		
	}
}
