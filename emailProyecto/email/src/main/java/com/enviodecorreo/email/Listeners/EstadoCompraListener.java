package com.enviodecorreo.email.Listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.enviodecorreo.email.config.RabbitConfig;
import com.enviodecorreo.email.Controllers.EstadoCompraMessage;
import com.enviodecorreo.email.Entity.Email;
import com.enviodecorreo.email.services.EmailService;

@Component
public class EstadoCompraListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void recibirMensaje(EstadoCompraMessage mensaje) {

        System.out.println("📥 [EMAIL] Mensaje recibido desde RabbitMQ");
        System.out.println("  Cliente: " + mensaje.getNombreCliente());
        System.out.println("  Correo cliente:  " + mensaje.getCorreoCliente());
        System.out.println("  Correo destino:  " + mensaje.getCorreoDestino());
        System.out.println("  Estado:  " + mensaje.getEstadoTransaccion());
        System.out.println("  Paquete: " + mensaje.getNombrePaquete());
        System.out.println("  Total:   " + mensaje.getTotal());

        
        Email email = new Email();

        email.setFrom("gianfrancowii09@gmail.com"); 
        email.setTo(
            mensaje.getCorreoDestino() != null && !mensaje.getCorreoDestino().isEmpty()
                ? mensaje.getCorreoDestino()
                : mensaje.getCorreoCliente()
        );

        email.setSubject("Estado de tu compra: " + mensaje.getEstadoTransaccion());

        String cuerpo = String.format(
            """
            Hola %s,

            Tu compra del paquete '%s' por un total de %.2f está en estado: %s.
            Fecha: %s

            ¡Gracias por tu compra!
            """,
            mensaje.getNombreCliente(),
            mensaje.getNombrePaquete(),
            mensaje.getTotal(),
            mensaje.getEstadoTransaccion(),
            mensaje.fecha()   
        );

        email.setText(cuerpo);

        // Campos adicionales que ya existen en la entidad Email
        email.setNombreCliente(mensaje.getNombreCliente());
        email.setCorreoCliente(mensaje.getCorreoCliente());
        email.setCorreoDestino(email.getTo());
        email.setEstadoTransaccion(mensaje.getEstadoTransaccion());
        email.setNombrePaquete(mensaje.getNombrePaquete());
        email.setTotal(mensaje.getTotal());
        email.setFecha(mensaje.fecha());

        
        emailService.sendEmail(email);
    }
}
