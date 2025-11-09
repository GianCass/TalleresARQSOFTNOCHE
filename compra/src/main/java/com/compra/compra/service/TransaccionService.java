package com.compra.compra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.entity.*;
import com.compra.compra.enums.StatusTransaccion;
import com.compra.compra.repository.*;
import com.compra.compra.dto.PagoDTO;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepo;
    @Autowired
    private TransaccionItemRepository transaccionItemRepo;
    @Autowired
    private CarritoItemRepository carritoItemRepo;
    @Autowired
    private CarritosRepository carritoRepo;
    @Autowired
    private TarjetaRepository tarjetaRepo;
    @Autowired
    private PaqueteRepository paqueteRepo;

    public Transacciones procesarCompra(PagoDTO pagoDTO) {
        List<CarritoItems> items = carritoItemRepo.findAll().stream()
            .filter(i -> i.getCarritoId().equals(pagoDTO.getCarritoId())).toList();

        double total = items.stream().mapToDouble(i -> {
            Paquete paquete = paqueteRepo.findById(i.getPaqueteId()).orElseThrow();
            return paquete.getPrecio() * i.getCantidad();
        }).sum();

        Tarjeta tarjeta = tarjetaRepo.findById(pagoDTO.getTarjetaId()).orElseThrow();
        if (tarjeta.getSaldo() < total) {
            throw new RuntimeException("Saldo insuficiente en la tarjeta.");
        }
        tarjeta.setSaldo(tarjeta.getSaldo() - total);
        tarjetaRepo.save(tarjeta);

        Transacciones transaccion = new Transacciones();
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setStatus(StatusTransaccion.EXITOSA);
        transaccion.setTotal(total);
        transaccion.setCedula(pagoDTO.getCedulaCliente());
        transaccion.setTarjetaId(pagoDTO.getTarjetaId());
        transaccion = transaccionRepo.save(transaccion);

        for (CarritoItems item : items) {
            Paquete paquete = paqueteRepo.findById(item.getPaqueteId()).orElseThrow();
            TransaccionesItems txItem = new TransaccionesItems();
            txItem.setTransaccionId(transaccion.getId());
            txItem.setPaqueteId(paquete.getCodigo());
            txItem.setCantidad(item.getCantidad());
            txItem.setPrecioUnitario(paquete.getPrecio());
            transaccionItemRepo.save(txItem);
        }
        carritoRepo.deleteById(pagoDTO.getCarritoId());
        return transaccion;
    }

    public List<Transacciones> findAll() { return transaccionRepo.findAll(); }
}