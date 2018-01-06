package com.eshop.product.rest.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.eshop.product.entity.ProductIdCounter;
import com.eshop.product.rest.ProductIdGenerator;

@Service
public class ProductIdGeneratorImpl implements ProductIdGenerator 
{
    @Autowired 
    private MongoOperations mongoOperations;

	public String getNextProductId(String seqName)
	{
	    ProductIdCounter productIdCounter = mongoOperations.findAndModify(
	    	      query(where("_id").is(seqName)), 
	    	      new Update().inc("seq", 1),
	    	      options().returnNew(true).upsert(true),
	    	      ProductIdCounter.class);
	    	       
	    	    return "P_" + productIdCounter.getSeq();
	}

}
