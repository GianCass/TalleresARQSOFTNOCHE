package com.compra.compra.repository;

import com.compra.compra.entity.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends MongoRepository<Compra, String> {
}
