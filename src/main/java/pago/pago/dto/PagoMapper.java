package pago.pago.dto;

import pago.pago.entity.Pago;

public final class PagoMapper {
    private PagoMapper(){}

    public static Pago toEntity(pagoRequestDTO dto){
        Pago p = new Pago();
        p.setCompraId(dto.compraId());
        p.setMonto(dto.monto());
        p.setMedio(dto.medio());
        return p;
    }

    public static pagoResponseDTO toResponse(Pago p){
        return new pagoResponseDTO(
                p.getId(),
                p.getCompraId(),
                p.getMonto(),
                p.getMedio(),
                p.getEstado(),
                p.getReferenciaPasarela(),
                p.getUrlRedireccion()
        );
    }
}
