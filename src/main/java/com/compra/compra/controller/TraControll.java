package com.compra.compra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.compra.compra.dto.EstadoCompraMessage;
import com.compra.compra.service.ColaEstadoCompra;

@RestController
@RequestMapping("/transacciones")
public class TraControll {

    @Autowired
    private ColaEstadoCompra colaEstadoCompra;

    @GetMapping("/cola")
    
    public ResponseEntity<String> enviarMensajeJson(@RequestBody EstadoCompraMessage mensaje) {

        colaEstadoCompra.send(mensaje);

        return ResponseEntity.ok("Mensaje JSON enviado a RabbitMQ");
    }

}
