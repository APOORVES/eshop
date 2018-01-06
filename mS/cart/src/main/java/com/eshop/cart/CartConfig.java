package com.eshop.cart;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.eshop.cart.repository.CartRepository;
import com.eshop.cart.rest.impl.CartServiceImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/*@EnableAutoConfiguration
@ComponentScan(basePackages = "com.eshop.cart")
*/

@Configuration
@EnableMongoRepositories(basePackageClasses=CartRepository.class)
public class CartConfig 
{
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() 
	{
		return new SpringBus();
	}
	
	@Bean(destroyMethod = "destroy") 
	@DependsOn("cxf")
    public Server jaxRsServer() 
    {
        final JAXRSServerFactoryBean jarsServerFactoryBean = new JAXRSServerFactoryBean();
        jarsServerFactoryBean.setProvider(new JacksonJsonProvider());
        jarsServerFactoryBean.setBus(cxf());
        jarsServerFactoryBean.setServiceBean(cartServiceImpl);
        jarsServerFactoryBean.setAddress("/");
        return jarsServerFactoryBean.create();
    }
    
    @Bean
    public ServletRegistrationBean cxfServlet() 
    {
        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
	
	
	
}
