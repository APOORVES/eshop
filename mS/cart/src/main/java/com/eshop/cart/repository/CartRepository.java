package com.eshop.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eshop.cart.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>
{
	//Cart findById(String id);
	
	

}
