package com.compra.compra.controller;

import com.compra.compra.entity.Tarjeta;
import com.compra.compra.service.TarjetaService;
import com.compra.compra.dto.RecargaTarjetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<Tarjeta> crear(@RequestBody Tarjeta tarjeta) {
        return ResponseEntity.ok(tarjetaService.crearTarjeta(tarjeta));
    }

    @GetMapping
    public ResponseEntity<List<Tarjeta>> listar() {
        return ResponseEntity.ok(tarjetaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> buscarPorId(@PathVariable String id) {
        return tarjetaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-cliente/{cedulaCliente}")
    public ResponseEntity<List<Tarjeta>> listarPorCliente(@PathVariable String cedulaCliente) {
        return ResponseEntity.ok(tarjetaService.findByCedulaCliente(cedulaCliente));
    }

    @PostMapping("/recarga")
    public ResponseEntity<Tarjeta> recargar(@RequestBody RecargaTarjetaDTO dto) {
        return ResponseEntity.ok(tarjetaService.recargarSaldo(dto.getTarjetaId(), dto.getMonto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        tarjetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}