package com.compra.compra.dto;

import java.util.List;
import java.util.Objects;

import com.compra.compra.entity.Carrito;
import com.compra.compra.entity.Paquete;

public class CarritoMapper {

    public static Carrito toEntity(CarritoRequestDTO dto) {
        Carrito c = new Carrito();
        c.setClienteId(dto.clienteId());
        if (dto.items() != null) {
            c.setItems(dto.items().stream().map(CarritoMapper::toEntity).toList());
        }
        return c;
    }

    public static CarritoResponseDTO toResponse(Carrito c) {
        List<paqueteDTO> items = c.getItems() == null ? List.of()
                : c.getItems().stream().map(CarritoMapper::toDto).toList();

        double total = c.getItems() == null ? 0.0
                : c.getItems().stream()
                    .map(Paquete::getPrecio)
                    .filter(Objects::nonNull)
                    .mapToDouble(Double::doubleValue)
                    .sum();

        int totalItems = c.getItems() == null ? 0 : c.getItems().size();

        return new CarritoResponseDTO(
            c.getId(),
            c.getClienteId(),
            items,
            total,
            totalItems
        );
    }

    private static Paquete toEntity(paqueteDTO dto) {
        Paquete p = new Paquete();
        p.setCodigo(dto.codigo());
        p.setDestino(dto.destino());
        p.setPrecio(dto.precio());
        p.setEstadoValidacion(dto.estad());
        return p;
    }

    private static paqueteDTO toDto(Paquete p) {
        return new paqueteDTO(
            p.getCodigo(),
            p.getDestino(),
            p.getPrecio(),
            p.getEstadoValidacion()
        );
    }
}
