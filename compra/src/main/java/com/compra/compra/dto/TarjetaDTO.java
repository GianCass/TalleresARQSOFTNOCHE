package com.compra.compra.dto;

import com.compra.compra.enums.TipoTarjeta;

public class TarjetaDTO {
    private String id;
    private String fechaVencimiento;
    private String numeroTarjeta;
    private Double saldo;
    private TipoTarjeta tipo;
    private String cedulaCliente;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public TipoTarjeta getTipo() { return tipo; }
    public void setTipo(TipoTarjeta tipo) { this.tipo = tipo; }
    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}