package com.compra.compra.dto;

public class TransaccionItemDTO {
    private String id;
    private Integer cantidad;
    private String paqueteId;
    private Double precioUnitario;
    private String transaccionId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public String getPaqueteId() { return paqueteId; }
    public void setPaqueteId(String paqueteId) { this.paqueteId = paqueteId; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public String getTransaccionId() { return transaccionId; }
    public void setTransaccionId(String transaccionId) { this.transaccionId = transaccionId; }
}
