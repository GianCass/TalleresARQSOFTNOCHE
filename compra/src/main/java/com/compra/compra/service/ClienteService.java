package com.compra.compra.service;

import com.compra.compra.entity.Cliente;

public interface ClienteService {

    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorUsername(String username);
    Cliente actualizarCliente(Cliente cliente);
    Cliente eliminarCliente(String username);
    Cliente obtenerClientePorIDCliente(String idCliente);
    
}
