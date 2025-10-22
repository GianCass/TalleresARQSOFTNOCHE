package com.compra.publicador.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "estadoCompraQueue";

    @Bean
    public Queue estadoCompraQueue() {
        
        return new Queue(QUEUE_NAME, true);
    }
}
