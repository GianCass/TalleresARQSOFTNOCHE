package com.compra.publicador.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.compra.publicador.Service.ColaEstadoCompra;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    ColaEstadoCompra colaEstadoCompra;

    @GetMapping("/cola")
    public ResponseEntity<String> prueba() {
        String estadoJson = "EXITOSA";

        colaEstadoCompra.send(estadoJson);

        return ResponseEntity.ok("OK");
    }

}
