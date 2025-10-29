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
        Pago p = PagoMapper.toEntity(request);

        // Simulación de pasarela
        p.setEstado(EstadoPago.PENDIENTE);
        p.setReferenciaPasarela("REF-" + UUID.randomUUID());
        p.setUrlRedireccion("https://checkout.mock/" + p.getReferenciaPasarela());

        return PagoMapper.toResponse(pagoRepository.save(p));
    }

    @Override
    public pagoResponseDTO confirmar(String pagoId, String tokenPasarela) {
        Pago p = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado: " + pagoId));

        boolean ok = tokenPasarela != null && tokenPasarela.endsWith("OK");
        p.setEstado(ok ? EstadoPago.AUTORIZADO : EstadoPago.RECHAZADO);

        return PagoMapper.toResponse(pagoRepository.save(p));
    }

    @Override
    public pagoResponseDTO buscarPorId(String pagoId) {
        return pagoRepository.findById(pagoId)
                .map(PagoMapper::toResponse)
                .orElseThrow(() -> new NoSuchElementException("Pago no encontrado: " + pagoId));
    }
}
