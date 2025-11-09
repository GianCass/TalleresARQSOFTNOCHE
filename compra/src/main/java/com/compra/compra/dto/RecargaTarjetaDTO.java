package com.compra.compra.dto;

public class RecargaTarjetaDTO {
    private String tarjetaId;
    private Double monto;

    public String getTarjetaId() { return tarjetaId; }
    public void setTarjetaId(String tarjetaId) { this.tarjetaId = tarjetaId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
}