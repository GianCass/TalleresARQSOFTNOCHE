package com.compra.compra.Config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


@Configuration
public class RabbitConfig {

    public static final String COLA_CORREO = "estadoCompraQueue";
    @Bean
    public Queue estadoCompraQueue() { return new Queue(COLA_CORREO, true); }

    public static final String COLA_PAGO = "compraPagoQueue";
    @Bean
    public Queue compraPagoQueue() { return new Queue(COLA_PAGO, true); }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    public static final String COLA_CONFIRMACION = "pagoConfirmadoQueue";

    @Bean
    public Queue pagoConfirmadoQueue() {
        return new Queue(COLA_CONFIRMACION, true);
    }

}

