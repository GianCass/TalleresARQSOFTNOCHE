package com.compra.publicador.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.publicador.Config.RabbitConfig;

@Service
public class ColaEstadoCompra {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String estadoJson) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, estadoJson);
        System.out.println("Mensaje enviado: " + estadoJson);
    }
}

