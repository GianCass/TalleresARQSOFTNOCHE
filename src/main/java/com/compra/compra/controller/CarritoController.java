package com.compra.compra.controller;

import java.net.URI;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.compra.compra.dto.CarritoRequestDTO;
import com.compra.compra.dto.CarritoResponseDTO;
import com.compra.compra.dto.paqueteDTO;
import com.compra.compra.service.CarritoService;

@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService service;

    @PostMapping
    public ResponseEntity<CarritoResponseDTO> crear(@Valid @RequestBody CarritoRequestDTO request) {
        CarritoResponseDTO saved = service.crear(request);
        return ResponseEntity.created(URI.create("/api/carritos/" + saved.id())).body(saved);
    }

    @GetMapping
    public Page<CarritoResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public CarritoResponseDTO obtenerPorId(@PathVariable String id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/by-cliente/{clienteId}")
    public CarritoResponseDTO obtenerPorCliente(@PathVariable String clienteId) {
        return service.obtenerPorCliente(clienteId);
    }

    @PostMapping("/{id}/items")
    public CarritoResponseDTO agregarItem(@PathVariable String id, @Valid @RequestBody paqueteDTO item) {
        return service.agregarItem(id, item);
    }

    @DeleteMapping("/{id}/items/{codigo}")
    public CarritoResponseDTO eliminarItem(@PathVariable String id, @PathVariable String codigo) {
        return service.eliminarItem(id, codigo);
    }

    @DeleteMapping("/{id}/items")
    public CarritoResponseDTO vaciar(@PathVariable String id) {
        return service.vaciar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
