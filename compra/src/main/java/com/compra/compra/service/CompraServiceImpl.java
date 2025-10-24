package com.compra.compra.service;

import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;
import com.compra.compra.entity.Compra;
import com.compra.compra.entity.EstadoCompra;
import com.compra.compra.repository.CompraRepository;
import com.compra.compra.dto.CompraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    @Override
    public compraResponseDTO registrarCompra(compraRequestDTO request) {

        Compra compra = CompraMapper.toEntity(request);

        compra.setEstado(EstadoCompra.PENDIENTE);

        if (compra.getPaquetes() != null && !compra.getPaquetes().isEmpty()) {
            double total = compra.getPaquetes().stream()
                    .mapToDouble(p -> p.getPrecio() != null ? p.getPrecio() : 0.0)
                    .sum();
            compra.setPrecioTotal(total);
        } else {
            compra.setPrecioTotal(0.0);
        }

        
        Compra saved = compraRepository.save(compra);

        
        return CompraMapper.toResponse(saved);
    }

    @Override
    public compraResponseDTO continuarCompra(String idCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + idCompra));

        if (compra.getEstado() == EstadoCompra.PENDIENTE) {
            compra.setEstado(EstadoCompra.APROBADA);
        }

        Compra saved = compraRepository.save(compra);
        return CompraMapper.toResponse(saved);
    }

    @Override
    public compraResponseDTO buscarPorId(String idCompra) {
        return compraRepository.findById(idCompra)
                .map(CompraMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + idCompra));
    }
}
