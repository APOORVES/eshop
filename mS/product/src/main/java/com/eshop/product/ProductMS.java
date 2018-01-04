package com.eshop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.eshop.product")

@SpringBootApplication
public class ProductMS 
{

	public static void main(String[] args) 
	{
		//SpringApplication.run(ProductConfig.class, args);
		SpringApplication.run(ProductMS.class, args);

/*	    SpringApplication app = new SpringApplication(ProductMS.class);
	    app.setWebEnvironment(false);
	    ConfigurableApplicationContext ctx = app.run(args);		*/
		
	}
}
