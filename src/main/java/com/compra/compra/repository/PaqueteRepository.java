package com.compra.compra.repository;

import com.compra.compra.entity.Paquete;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaqueteRepository extends MongoRepository<Paquete, String>{
    
}
