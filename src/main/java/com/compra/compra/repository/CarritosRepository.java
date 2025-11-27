package com.compra.compra.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.compra.compra.entity.Carrito;

public interface CarritosRepository extends MongoRepository<Carrito, String> {
    Optional<Carrito> findByClienteId(String clienteId);
}
