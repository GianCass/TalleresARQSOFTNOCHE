package com.compra.compra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compra.compra.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {


    
}
