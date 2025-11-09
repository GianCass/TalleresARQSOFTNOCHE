package com.compra.compra.dto;

import com.compra.compra.enums.StatusTransaccion;

import java.time.LocalDateTime;

public class TransaccionDTO {
    private String id;
    private LocalDateTime fecha;
    private StatusTransaccion status;
    private Double total;
    private String cedula;
    private String tarjetaId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public StatusTransaccion getStatus() { return status; }
    public void setStatus(StatusTransaccion status) { this.status = status; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getTarjetaId() { return tarjetaId; }
    public void setTarjetaId(String tarjetaId) { this.tarjetaId = tarjetaId; }
}