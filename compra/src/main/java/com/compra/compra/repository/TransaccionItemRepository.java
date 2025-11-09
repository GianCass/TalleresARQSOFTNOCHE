package com.compra.compra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compra.compra.entity.TransaccionesItems;

public interface TransaccionItemRepository extends MongoRepository<TransaccionesItems, String>{
    
}
