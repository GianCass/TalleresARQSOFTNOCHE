package com.compra.compra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "paquetes")
public class Paquete {

    @Id
    private String codigo; 
    private String destino;
    private Double precio;
    private EstadoValidacionOTN estadoValidacion;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public EstadoValidacionOTN getEstadoValidacion() { return estadoValidacion; }
    public void setEstadoValidacion(EstadoValidacionOTN estadoValidacion) { this.estadoValidacion = estadoValidacion; }


}

