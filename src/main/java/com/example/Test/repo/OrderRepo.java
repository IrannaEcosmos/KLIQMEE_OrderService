package com.example.Test.repo;

import com.example.Test.dto.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
    Optional<Order> findByIdAndUserId(String id, String userId);
    Optional<Order> findFirstByUserId(String userId);
}