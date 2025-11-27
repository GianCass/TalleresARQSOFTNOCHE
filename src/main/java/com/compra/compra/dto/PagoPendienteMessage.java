package com.compra.compra.dto;

import java.io.Serializable;

public class PagoPendienteMessage implements Serializable {
    private String cedulaCliente;
    private String compraId;
    private Double valor;

    public PagoPendienteMessage() {}

    public PagoPendienteMessage(String cedulaCliente, String compraId, Double valor) {
        this.cedulaCliente = cedulaCliente;
        this.compraId = compraId;
        this.valor = valor;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getCompraId() {
        return compraId;
    }

    public void setCompraId(String compraId) {
        this.compraId = compraId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
