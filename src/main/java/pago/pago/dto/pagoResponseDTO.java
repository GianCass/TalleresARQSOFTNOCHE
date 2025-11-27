package pago.pago.dto;

import pago.pago.entity.EstadoPago;
import pago.pago.entity.MedioPago;

public final class pagoResponseDTO {
    private final String id;
    private final String compraId;
    private final Double monto;
    private final MedioPago medio;
    private final EstadoPago estado;
    private final String referenciaPasarela;
    private final String urlRedireccion;

    public pagoResponseDTO(
            String id,
            String compraId,
            Double monto,
            MedioPago medio,
            EstadoPago estado,
            String referenciaPasarela,
            String urlRedireccion
    ) {
        this.id = id;
        this.compraId = compraId;
        this.monto = monto;
        this.medio = medio;
        this.estado = estado;
        this.referenciaPasarela = referenciaPasarela;
        this.urlRedireccion = urlRedireccion;
    }

    public String id() { return id; }
    public String compraId() { return compraId; }
    public Double monto() { return monto; }
    public MedioPago medio() { return medio; }
    public EstadoPago estado() { return estado; }
    public String referenciaPasarela() { return referenciaPasarela; }
    public String urlRedireccion() { return urlRedireccion; }
}
