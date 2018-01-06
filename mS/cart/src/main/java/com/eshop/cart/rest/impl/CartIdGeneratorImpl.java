package com.eshop.cart.rest.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.eshop.cart.entity.CartIdCounter;
import com.eshop.cart.rest.CartIdGenerator;

@Service
public class CartIdGeneratorImpl implements CartIdGenerator 
{
    @Autowired 
    private MongoOperations mongoOperations;

	public String getNextCartId(String seqName)
	{
	    CartIdCounter cartIdCounter = mongoOperations.findAndModify(
	    	      query(where("_id").is(seqName)), 
	    	      new Update().inc("seq", 1),
	    	      options().returnNew(true).upsert(true),
	    	      CartIdCounter.class);
	    	       
	    	    return "C_" + cartIdCounter.getSeq();
	}

}
