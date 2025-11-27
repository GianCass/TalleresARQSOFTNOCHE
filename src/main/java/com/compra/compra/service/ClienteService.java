package com.compra.compra.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.compra.compra.entity.Cliente;

public interface ClienteService {

    Cliente crear(Cliente cliente);

    Page<Cliente> listar(Pageable pageable);

    Optional<Cliente> obtenerPorId(String id);

    Cliente actualizar(String id, Cliente cliente);

    void eliminar(String id);

    Optional<Cliente> obtenerPorUsername(String username);
    boolean existeUsername(String username);
}
