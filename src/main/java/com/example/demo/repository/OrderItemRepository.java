package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.OrderItem;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

}
