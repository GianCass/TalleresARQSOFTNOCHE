package com.compra.compra.dto;

import com.compra.compra.entity.Compra;

public class CompraMapper {

    public static Compra toEntity(compraRequestDTO dto) {
        Compra c = new Compra();
        c.setCliente(dto.cliente());
        c.setPaquetes(dto.paquetes());
        return c;
    }

    public static compraResponseDTO toResponse(Compra c) {
        return new compraResponseDTO(
                c.getId(),
                c.getFechaRegistro(),
                c.getCliente(),
                c.getPaquetes(),
                c.getPrecioTotal(),
                c.getEstado()
        );
    }
}
