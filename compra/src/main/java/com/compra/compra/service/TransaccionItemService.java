package com.compra.compra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.entity.TransaccionesItems;
import com.compra.compra.repository.TransaccionItemRepository;

import java.util.List;

@Service
public class TransaccionItemService {
    @Autowired
    private TransaccionItemRepository transaccionItemRepo;

    public List<TransaccionesItems> obtenerItemsPorTransaccion(String transaccionId) {
        return transaccionItemRepo.findAll().stream()
            .filter(i -> i.getTransaccionId().equals(transaccionId)).toList();
    }

    public void eliminarItem(String id) { transaccionItemRepo.deleteById(id); }
}