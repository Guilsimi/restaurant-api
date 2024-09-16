package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    @Query("{ $and: [ { value: {$gte: ?1} }, { value: { $lte: ?2} } , { $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Item> fullSearch(String text, Double minValue, Double maxValue);

}
