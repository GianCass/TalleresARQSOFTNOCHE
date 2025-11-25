package com.enviodecorreo.email.Controllers;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class EstadoCompraMessage {

    private String nombreCliente;
    private String correoCliente;
    private String correoDestino;
    private String estadoTransaccion;
    private String nombrePaquete;
    private Double total;
    private LocalDate fecha;

    public EstadoCompraMessage() {}

    public EstadoCompraMessage(String nombreCliente, String correoCliente, String correoDestino,String estadoTransaccion,
                            String nombrePaquete, Double total, LocalDate fecha) {
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.correoDestino = correoDestino;
        this.estadoTransaccion = estadoTransaccion;
        this.nombrePaquete = nombrePaquete;
        this.total = total;
        this.fecha = fecha;
    }

    // --- getters y setters ---
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }

    public String getCorreoDestino() { return correoDestino; }
    public void setCorreoDestino(String correoDestino) { this.correoDestino = correoDestino; }

    public String getEstadoTransaccion() { return estadoTransaccion; }
    public void setEstadoTransaccion(String estadoTransaccion) { this.estadoTransaccion = estadoTransaccion; }

    public String getNombrePaquete() { return nombrePaquete; }
    public void setNombrePaquete(String nombrePaquete) { this.nombrePaquete = nombrePaquete; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public LocalDate fecha() { return fecha; }
    public void setfecha(LocalDate fecha) {this.fecha = fecha; }

}
