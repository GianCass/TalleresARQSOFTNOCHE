package pago.pago.dto;

import pago.pago.entity.MedioPago;

public final class pagoRequestDTO {
    private final String compraId;
    private final Double monto;
    private final MedioPago medio;

    public pagoRequestDTO(String compraId, Double monto, MedioPago medio) {
        this.compraId = compraId;
        this.monto = monto;
        this.medio = medio;
    }

    public String compraId() {
        return compraId;
    }

    public Double monto() {
        return monto;
    }

    public MedioPago medio() {
        return medio;
    }
}
