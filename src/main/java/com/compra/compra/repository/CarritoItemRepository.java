package com.compra.compra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compra.compra.entity.CarritoItems;

public interface CarritoItemRepository extends MongoRepository<CarritoItems, String> {
    
}
