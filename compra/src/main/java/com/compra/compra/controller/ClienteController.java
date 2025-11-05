package com.compra.compra.controller;

import java.net.URI;
import java.util.Map;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.compra.compra.entity.Cliente;
import com.compra.compra.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente in) {
        Cliente saved = service.crear(in);
        return ResponseEntity.created(URI.create("/api/clientes/" + saved.getIdCliente())).body(saved);
    }

    @GetMapping
    public Page<Cliente> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable String id) {
        return service.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable String id, @Valid @RequestBody Cliente in) {
        return service.actualizar(id, in);
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(@PathVariable String id, @RequestBody Map<String, Object> cambios) {
        Cliente actual = service.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));

        if (cambios.containsKey("nombre"))   actual.setNombre((String) cambios.get("nombre"));
        if (cambios.containsKey("telefono")) actual.setTelefono((String) cambios.get("telefono"));
        if (cambios.containsKey("username")) actual.setUsername((String) cambios.get("username"));
        if (cambios.containsKey("idCliente")) actual.setIdCliente(String.valueOf(cambios.get("idCliente")));

        return service.actualizar(id, actual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
