package com.compra.compra.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compra.compra.dto.CarritoMapper;
import com.compra.compra.dto.CarritoRequestDTO;
import com.compra.compra.dto.CarritoResponseDTO;
import com.compra.compra.dto.paqueteDTO;
import com.compra.compra.entity.Carrito;
import com.compra.compra.entity.Paquete;
import com.compra.compra.repository.CarritosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritosRepository repo;

    @Override
    public CarritoResponseDTO crear(CarritoRequestDTO request) {
        Carrito carrito = CarritoMapper.toEntity(request);
        return CarritoMapper.toResponse(repo.save(carrito));
    }

    @Override
    public Page<CarritoResponseDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(CarritoMapper::toResponse);
    }

    @Override
    public CarritoResponseDTO obtenerPorId(String id) {
        Carrito c = repo.findById(id).orElseThrow(() ->
            new NoSuchElementException("Carrito no encontrado: " + id));
        return CarritoMapper.toResponse(c);
    }

    @Override
    public CarritoResponseDTO obtenerPorCliente(String clienteId) {
        Carrito c = repo.findByClienteId(clienteId).orElseThrow(() ->
            new NoSuchElementException("Carrito no encontrado para cliente: " + clienteId));
        return CarritoMapper.toResponse(c);
    }

    @Override
    public CarritoResponseDTO agregarItem(String carritoId, paqueteDTO item) {
        Carrito c = repo.findById(carritoId).orElseThrow(() ->
            new NoSuchElementException("Carrito no encontrado: " + carritoId));

        Paquete nuevo = new Paquete();
        nuevo.setCodigo(item.codigo());
        nuevo.setDestino(item.destino());
        nuevo.setPrecio(item.precio());
        nuevo.setEstadoValidacion(item.estad());

        c.getItems().add(nuevo);
        return CarritoMapper.toResponse(repo.save(c));
    }

    @Override
    public CarritoResponseDTO eliminarItem(String carritoId, String codigoPaquete) {
        Carrito c = repo.findById(carritoId).orElseThrow(() ->
            new NoSuchElementException("Carrito no encontrado: " + carritoId));

        c.getItems().removeIf(p -> codigoPaquete.equals(p.getCodigo()));
        return CarritoMapper.toResponse(repo.save(c));
    }

    @Override
    public CarritoResponseDTO vaciar(String carritoId) {
        Carrito c = repo.findById(carritoId).orElseThrow(() ->
            new NoSuchElementException("Carrito no encontrado: " + carritoId));
        c.getItems().clear();
        return CarritoMapper.toResponse(repo.save(c));
    }

    @Override
    public void eliminar(String carritoId) {
        if (!repo.existsById(carritoId)) {
            throw new NoSuchElementException("Carrito no encontrado: " + carritoId);
        }
        repo.deleteById(carritoId);
    }
}
