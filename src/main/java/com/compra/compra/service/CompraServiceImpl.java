package com.compra.compra.service;

import com.compra.compra.dto.compraRequestDTO;
import com.compra.compra.dto.compraResponseDTO;
import com.compra.compra.dto.CompraMapper;
import com.compra.compra.dto.PagoPendienteMessage; 
import com.compra.compra.entity.Compra;
import com.compra.compra.entity.EstadoCompra;
import com.compra.compra.repository.CompraRepository;
import com.compra.compra.Config.RabbitConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final RabbitTemplate rabbitTemplate; 

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

        Compra savedCompra = compraRepository.save(compra);

        PagoPendienteMessage message = new PagoPendienteMessage(
            savedCompra.getCliente().getCedula(),     
            savedCompra.getId(),                      
            savedCompra.getPrecioTotal()              
        );
        rabbitTemplate.convertAndSend(RabbitConfig.COLA_PAGO, message);

        return CompraMapper.toResponse(savedCompra);
    }

    @Override
    public compraResponseDTO continuarCompra(String idCompra) {
        Compra compra = compraRepository.findById(idCompra)
            .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + idCompra));

        if (compra.getEstado() == EstadoCompra.PENDIENTE) {
            compra.setEstado(EstadoCompra.VALIDANDO);
        }
        return CompraMapper.toResponse(compraRepository.save(compra));
    }

    @Override
    public compraResponseDTO buscarPorId(String idCompra) {
        return compraRepository.findById(idCompra)
            .map(CompraMapper::toResponse)
            .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + idCompra));
    }

    @Override
    public Page<compraResponseDTO> listar(Pageable pageable) {
        return compraRepository.findAll(pageable).map(CompraMapper::toResponse);
    }

    @Override
    public Page<compraResponseDTO> listarPorEstado(EstadoCompra estado, Pageable pageable) {
        return compraRepository.findAllByEstado(estado, pageable).map(CompraMapper::toResponse);
    }

    @Override
    public compraResponseDTO actualizar(String idCompra, compraRequestDTO request) {
        Compra existente = compraRepository.findById(idCompra)
            .orElseThrow(() -> new NoSuchElementException("Compra no encontrada: " + idCompra));

        Compra nuevosDatos = CompraMapper.toEntity(request);
        existente.setCliente(nuevosDatos.getCliente());
        existente.setPaquetes(nuevosDatos.getPaquetes());

        double total = existente.getPaquetes() == null ? 0.0 :
            existente.getPaquetes().stream()
                .mapToDouble(p -> p.getPrecio() != null ? p.getPrecio() : 0.0)
                .sum();
        existente.setPrecioTotal(total);

        return CompraMapper.toResponse(compraRepository.save(existente));
    }

    @Override
    public void eliminar(String idCompra) {
        if (!compraRepository.existsById(idCompra)) {
            throw new NoSuchElementException("Compra no encontrada: " + idCompra);
        }
        compraRepository.deleteById(idCompra);
    }
}
