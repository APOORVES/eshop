package com.eshop.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eshop.product.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long>
{
	//Product findById(String id);
	
	

}
