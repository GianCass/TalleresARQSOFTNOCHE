package com.compra.compra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compra.compra.entity.Paquete;
import com.compra.compra.repository.PaqueteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteService {
    @Autowired
    private PaqueteRepository paqueteRepo;

    public Paquete crearPaquete(Paquete paquete) { return paqueteRepo.save(paquete); }
    public List<Paquete> findAll() { return paqueteRepo.findAll(); }
    public Optional<Paquete> findById(String id) { return paqueteRepo.findById(id); }
    public void deleteById(String id) { paqueteRepo.deleteById(id); }
}