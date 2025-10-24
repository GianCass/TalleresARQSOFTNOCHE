package com.compra.compra.controller;

import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;
import com.compra.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
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
}
