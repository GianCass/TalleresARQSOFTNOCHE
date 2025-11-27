package com.compra.compra.dto;

public class PagoDTO {
    private String carritoId;
    private String tarjetaId;
    private String idCliente;
    private String cedulaCliente;
    private String compraId;

    public String getCarritoId() { return carritoId; }
    public void setCarritoId(String carritoId) { this.carritoId = carritoId; }
    public String getTarjetaId() { return tarjetaId; }
    public void setTarjetaId(String tarjetaId) { this.tarjetaId = tarjetaId; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
    public String getCompraId() { return compraId; } 
    public void setCompraId(String compraId) { this.compraId = compraId; }
}
