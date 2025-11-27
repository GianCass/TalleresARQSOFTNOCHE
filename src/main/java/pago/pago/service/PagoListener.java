package pago.pago.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pago.pago.dto.PagoPendienteMessage;   
import pago.pago.dto.pagoRequestDTO;        
import pago.pago.entity.MedioPago;          
import pago.pago.service.PagoService;
import pago.pago.config.RabbitConfig;       

@Service
public class PagoListener {

    private final PagoService pagoService;

    @Autowired
    public PagoListener(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @RabbitListener(queues = RabbitConfig.COLA_PAGO)
    public void recibirPagoPendiente(PagoPendienteMessage mensaje) {
        
        pagoRequestDTO request = new pagoRequestDTO(
            mensaje.getCompraId(),
            mensaje.getValor(),
            MedioPago.TARJETA 
        );
        pagoService.iniciar(request);

        System.out.println("Pago recibido desde cola y procesado. Compra ID: " + mensaje.getCompraId());
    }
}
