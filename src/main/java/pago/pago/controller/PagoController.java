package pago.pago.controller;

import pago.pago.dto.pagoRequestDTO;
import pago.pago.dto.pagoResponseDTO;
import pago.pago.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<pagoResponseDTO> iniciar(@RequestBody pagoRequestDTO request) {
        var creado = pagoService.iniciar(request);
        return ResponseEntity.created(URI.create("/api/pagos/" + creado.id())).body(creado);
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<pagoResponseDTO> confirmar(@PathVariable String id, @RequestParam String token) {
        return ResponseEntity.ok(pagoService.confirmar(id, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<pagoResponseDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }
}
