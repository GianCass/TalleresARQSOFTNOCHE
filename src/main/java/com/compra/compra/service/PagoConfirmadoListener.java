package com.compra.compra.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.Config.RabbitConfig;
import com.compra.compra.dto.PagoConfirmadoMessage;
import com.compra.compra.entity.Compra;
import com.compra.compra.entity.EstadoCompra;
import com.compra.compra.repository.CompraRepository;

import java.util.NoSuchElementException;

@Service
public class PagoConfirmadoListener {

    @Autowired
    private CompraRepository compraRepository;

    @RabbitListener(queues = RabbitConfig.COLA_CONFIRMACION)
    public void recibirConfirmacionPago(PagoConfirmadoMessage message) {
        Compra compra = compraRepository.findById(message.getCompraId())
            .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + message.getCompraId()));

        compra.setEstado(EstadoCompra.APROBADA); 

        compraRepository.save(compra);

        System.out.println("Compra " + compra.getId() + " pagada por " +
                message.getCedulaCliente() +
                ". Valor pagado: " + message.getValorPagado());
    }
}
