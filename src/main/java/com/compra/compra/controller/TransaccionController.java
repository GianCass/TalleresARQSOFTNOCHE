package com.compra.compra.controller;

import com.compra.compra.entity.Transacciones;
import com.compra.compra.service.TransaccionService;
import com.compra.compra.dto.PagoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/compra")
    public ResponseEntity<Transacciones> procesarCompra(@RequestBody PagoDTO dto) {
        return ResponseEntity.ok(transaccionService.procesarCompra(dto));
    }

    @GetMapping
    public ResponseEntity<List<Transacciones>> listar() {
        return ResponseEntity.ok(transaccionService.findAll());
    }
}