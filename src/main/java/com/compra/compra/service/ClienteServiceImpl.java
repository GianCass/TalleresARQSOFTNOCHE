package com.compra.compra.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compra.compra.entity.Cliente;
import com.compra.compra.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    @Override
    public Cliente crear(Cliente cliente) {
        if (cliente.getUsername() != null && repo.existsByUsername(cliente.getUsername())) {
            throw new IllegalArgumentException("Username ya existe: " + cliente.getUsername());
        }
        return repo.save(cliente);
    }

    @Override
    public Page<Cliente> listar(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Optional<Cliente> obtenerPorId(String id) {
        return repo.findById(id);
    }

    @Override
    public Cliente actualizar(String id, Cliente in) {
        Cliente actual = repo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado: " + id));

        actual.setNombre(in.getNombre());
        actual.setTelefono(in.getTelefono());
        actual.setUsername(in.getUsername());
        actual.setIdCliente(in.getIdCliente());

        return repo.save(actual);
    }

    @Override
    public void eliminar(String id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public Optional<Cliente> obtenerPorUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public boolean existeUsername(String username) {
        return repo.existsByUsername(username);
    }
}
