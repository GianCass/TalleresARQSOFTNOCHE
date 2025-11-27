package com.compra.compra.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public record CarritoRequestDTO(
    @NotBlank String clienteId,
    List<paqueteDTO> items
) {}
