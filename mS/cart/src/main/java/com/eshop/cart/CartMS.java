package com.eshop.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages = "com.eshop.Cart")

@SpringBootApplication
public class CartMS 
{

	public static void main(String[] args) 
	{
		//SpringApplication.run(CartConfig.class, args);
		SpringApplication.run(CartMS.class, args);

/*	    SpringApplication app = new SpringApplication(CartMS.class);
	    app.setWebEnvironment(false);
	    ConfigurableApplicationContext ctx = app.run(args);		*/
		
	}
}
