package com.compra.compra.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.Config.RabbitConfig;
import com.compra.compra.dto.EstadoCompraMessage;

@Service
public class ColaEstadoCompra {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(EstadoCompraMessage  mensaje) {
        rabbitTemplate.convertAndSend(RabbitConfig.COLA_PAGO, mensaje);
        System.out.println("Mensaje tipo JSON enviado: " + mensaje);
    }
}

