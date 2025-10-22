package com.pago.consumidor.Listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pago.consumidor.Config.RabbitConfig;

@Component
public class EstadoCompraListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void recibirMensaje(String mensaje) {
        System.out.println(" [✔] Mensaje recibido desde RabbitMQ: " + mensaje);

        if ("EXITOSA".equalsIgnoreCase(mensaje)) {
            System.out.println("✅ La compra fue exitosa.");
        } else {
            System.out.println("⚠️ Estado desconocido: " + mensaje);
        }
    }
}
