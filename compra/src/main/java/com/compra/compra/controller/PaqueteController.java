package com.compra.compra.controller;

import com.compra.compra.entity.Paquete;
import com.compra.compra.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping
    public ResponseEntity<Paquete> crear(@RequestBody Paquete paquete) {
        return ResponseEntity.ok(paqueteService.crearPaquete(paquete));
    }

    @GetMapping
    public ResponseEntity<List<Paquete>> listar() {
        return ResponseEntity.ok(paqueteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> buscarPorId(@PathVariable String id) {
        return paqueteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        paqueteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}