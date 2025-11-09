package com.compra.compra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compra.compra.entity.Transacciones;

public interface TransaccionRepository extends MongoRepository<Transacciones, String>{
    
}
