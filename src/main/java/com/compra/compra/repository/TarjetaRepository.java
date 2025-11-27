package com.compra.compra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compra.compra.entity.Tarjeta;

public interface TarjetaRepository extends MongoRepository<Tarjeta, String> {
    
} 