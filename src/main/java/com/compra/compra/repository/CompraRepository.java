package com.compra.compra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compra.compra.entity.Compra;
import com.compra.compra.entity.EstadoCompra;

@Repository
public interface CompraRepository extends MongoRepository<Compra, String> {
    Page<Compra> findAllByEstado(EstadoCompra estado, Pageable pageable);
    List<Compra> findAllByCliente_Username(String username);
}
