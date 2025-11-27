package com.compra.compra.controller;

import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;
import com.compra.compra.entity.EstadoCompra;
import com.compra.compra.service.CompraService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<compraResponseDTO> crear(@RequestBody compraRequestDTO request) {
        var creada = compraService.registrarCompra(request);
        return ResponseEntity
                .created(URI.create("/api/compra/" + creada.id()))
                .body(creada);
    }

    @PutMapping("/{id}/continuar")
    public ResponseEntity<compraResponseDTO> continuar(@PathVariable String id) {
        var actualizada = compraService.continuarCompra(id);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<compraResponseDTO> buscar(@PathVariable String id) {
        var compra = compraService.buscarPorId(id);
        return ResponseEntity.ok(compra);
    }


    @GetMapping
    public Page<compraResponseDTO> listar(Pageable pageable,
                                        @RequestParam(required = false) EstadoCompra estado) {
        if (estado != null) {
            return compraService.listarPorEstado(estado, pageable);
        }
        return compraService.listar(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<compraResponseDTO> actualizar(@PathVariable String id,
                                                        @RequestBody compraRequestDTO request) {
        return ResponseEntity.ok(compraService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        compraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
