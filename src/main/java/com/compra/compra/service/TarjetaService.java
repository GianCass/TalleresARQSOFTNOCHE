package com.compra.compra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.entity.Tarjeta;
import com.compra.compra.repository.TarjetaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepo;

    public Tarjeta crearTarjeta(Tarjeta tarjeta) { return tarjetaRepo.save(tarjeta); }
    public List<Tarjeta> findAll() { return tarjetaRepo.findAll(); }
    public Optional<Tarjeta> findById(String id) { return tarjetaRepo.findById(id); }
    public List<Tarjeta> findByCedulaCliente(String cedula) {
        return tarjetaRepo.findAll().stream()
            .filter(t -> t.getCedula().equals(cedula)).toList();
    }
    public void deleteById(String id) { tarjetaRepo.deleteById(id); }

    public Tarjeta recargarSaldo(String id, Double monto) {
        Tarjeta tarjeta = tarjetaRepo.findById(id).orElseThrow();
        tarjeta.setSaldo(tarjeta.getSaldo() + monto);
        return tarjetaRepo.save(tarjeta);
    }
}