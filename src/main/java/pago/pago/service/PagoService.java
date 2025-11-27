package pago.pago.service;

import pago.pago.dto.pagoRequestDTO;
import pago.pago.dto.pagoResponseDTO;

public interface PagoService {
    pagoResponseDTO iniciar(pagoRequestDTO request);
    pagoResponseDTO confirmar(String pagoId, String tokenPasarela);
    pagoResponseDTO buscarPorId(String pagoId);
}
