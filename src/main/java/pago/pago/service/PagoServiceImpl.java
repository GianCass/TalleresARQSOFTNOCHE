package pago.pago.service;

import pago.pago.dto.PagoMapper;
import pago.pago.dto.pagoRequestDTO;
import pago.pago.dto.pagoResponseDTO;
import pago.pago.entity.EstadoPago;
import pago.pago.entity.Pago;
import pago.pago.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Override
    public pagoResponseDTO iniciar(pagoRequestDTO request) {
        Pago pago = PagoMapper.toEntity(request);

        pago.setReferenciaPasarela("PAGO-" + UUID.randomUUID());  
        return PagoMapper.toResponse(pagoRepository.save(pago));
    }

    @Override
    public pagoResponseDTO confirmar(String pagoId, String tokenPasarela) {
        Pago pago = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado: " + pagoId));

        pago.setEstado(EstadoPago.AUTORIZADO); 
        Pago savedPago = pagoRepository.save(pago);

        
        return PagoMapper.toResponse(savedPago);
    }

    @Override
    public pagoResponseDTO buscarPorId(String pagoId) {
        return pagoRepository.findById(pagoId)
                .map(PagoMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado: " + pagoId));
    }
}
