package com.compra.compra.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;
import com.compra.compra.entity.EstadoCompra;

public interface CompraService {
    compraResponseDTO registrarCompra(compraRequestDTO request);
    compraResponseDTO continuarCompra(String idCompra);
    compraResponseDTO buscarPorId(String idCompra);

    // nuevos
    Page<compraResponseDTO> listar(Pageable pageable);
    Page<compraResponseDTO> listarPorEstado(EstadoCompra estado, Pageable pageable);
    compraResponseDTO actualizar(String idCompra, compraRequestDTO request);
    void eliminar(String idCompra);
}
