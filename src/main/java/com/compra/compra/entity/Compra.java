package com.compra.compra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "compras")
public class Compra {

    @Id
    private String id;

    @CreatedDate
    private Instant fechaRegistro;

    private Cliente cliente;
    private List<Paquete> paquetes;
    private Double precioTotal;
    private EstadoCompra estado;

    public Compra() {
    }

    public Compra(String id, Instant fechaRegistro, Cliente cliente, List<Paquete> paquetes, Double precioTotal, EstadoCompra estado) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.cliente = cliente;
        this.paquetes = paquetes;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }
}
