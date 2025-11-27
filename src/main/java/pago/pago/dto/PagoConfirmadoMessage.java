package pago.pago.dto;

import java.io.Serializable;

public class PagoConfirmadoMessage implements Serializable {
    private String cedulaCliente;
    private String compraId;
    private Double valorPagado;

    public PagoConfirmadoMessage() {}
    public PagoConfirmadoMessage(String cedulaCliente, String compraId, Double valorPagado) {
        this.cedulaCliente = cedulaCliente;
        this.compraId = compraId;
        this.valorPagado = valorPagado;
    }
    // Getters y setters
    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
    public String getCompraId() { return compraId; }
    public void setCompraId(String compraId) { this.compraId = compraId; }
    public Double getValorPagado() { return valorPagado; }
    public void setValorPagado(Double valorPagado) { this.valorPagado = valorPagado; }
}
