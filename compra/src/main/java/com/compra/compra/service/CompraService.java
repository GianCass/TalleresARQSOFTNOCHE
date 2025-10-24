package com.compra.compra.service;

import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;

public interface CompraService {
    compraResponseDTO registrarCompra(compraRequestDTO request);
    compraResponseDTO continuarCompra(String idCompra);
    compraResponseDTO buscarPorId(String idCompra);
}
