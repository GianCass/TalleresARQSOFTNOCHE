package com.compra.compra.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compra.compra.entity.Cliente;
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Cliente> findByIdCliente(String idCliente);
}
