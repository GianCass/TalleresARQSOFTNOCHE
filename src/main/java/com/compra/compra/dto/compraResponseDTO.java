package com.compra.compra.dto;

import com.compra.compra.entity.Cliente;
import com.compra.compra.entity.EstadoCompra;
import com.compra.compra.entity.Paquete;
import java.time.Instant;
import java.util.List;

public record compraResponseDTO(
        String id,
        Instant fechaRegistro,
        Cliente cliente,
        List<Paquete> paquetes,
        Double precioTotal,
        EstadoCompra estado
) {}
