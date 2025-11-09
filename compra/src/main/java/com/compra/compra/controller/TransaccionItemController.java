package com.compra.compra.controller;

import com.compra.compra.entity.TransaccionesItems;
import com.compra.compra.service.TransaccionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones_items")
public class TransaccionItemController {

    @Autowired
    private TransaccionItemService transaccionItemService;

    @GetMapping("/por-transaccion/{id}")
    public ResponseEntity<List<TransaccionesItems>> porTransaccion(@PathVariable String id) {
        return ResponseEntity.ok(transaccionItemService.obtenerItemsPorTransaccion(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        transaccionItemService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }
}