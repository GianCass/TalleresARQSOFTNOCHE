package com.compra.compra.dto;

public class ClienteDTO {

    Long idCliente;
    String username;
    String name;
    String telefono;
    String cedula;

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public String getTelefono() { return telefono; }
    public void seTelefono(String telefono) { this.telefono = telefono; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
}
