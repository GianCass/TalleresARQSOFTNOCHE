// src/main/java/com/compra/compra/dto/CarritoResponseDTO.java
package com.compra.compra.dto;

import java.util.List;

public record CarritoResponseDTO(
    String id,
    String clienteId,
    List<paqueteDTO> items,
    Double total,
    Integer totalItems
) {}
