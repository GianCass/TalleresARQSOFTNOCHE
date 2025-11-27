package com.compra.compra.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.compra.compra.dto.CarritoRequestDTO;
import com.compra.compra.dto.CarritoResponseDTO;
import com.compra.compra.dto.paqueteDTO;

public interface CarritoService {

    CarritoResponseDTO crear(CarritoRequestDTO request);

    Page<CarritoResponseDTO> listar(Pageable pageable);

    CarritoResponseDTO obtenerPorId(String id);

    CarritoResponseDTO obtenerPorCliente(String clienteId);

    CarritoResponseDTO agregarItem(String carritoId, paqueteDTO item);

    CarritoResponseDTO eliminarItem(String carritoId, String codigoPaquete);

    CarritoResponseDTO vaciar(String carritoId);

    void eliminar(String carritoId);
}
