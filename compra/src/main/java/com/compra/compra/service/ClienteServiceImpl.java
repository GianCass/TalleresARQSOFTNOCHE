package com.compra.compra.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.compra.compra.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import com.compra.compra.entity.Cliente;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorUsername(String username) {
        return clienteRepository.findBy(username);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }
        return cliente;
    }

    @Override
    public Cliente obtenerClientePorIDCliente(String idCliente) {
        return clienteRepository.findById(idCliente);
    }
    



}
