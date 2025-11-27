package com.compra.compra.dto;

import com.compra.compra.entity.Cliente;
import com.compra.compra.entity.Paquete;
import java.util.List;


public record compraRequestDTO(
        Cliente cliente,
        List<Paquete> paquetes
) {}
