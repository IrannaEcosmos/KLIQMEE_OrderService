package com.example.Test.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Test.dto.Item;

@Repository
public interface ItemRepo extends MongoRepository<Item, String> {

}
