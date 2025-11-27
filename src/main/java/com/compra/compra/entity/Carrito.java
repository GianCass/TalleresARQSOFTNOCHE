package com.compra.compra.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("carritos")
public class Carrito {
    @Id
    private String id;
    private String clienteId;           
    private List<Paquete> items = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public List<Paquete> getItems() {
        return items;
    }

    public void setItems(List<Paquete> items) {
        this.items = items;
    }
    }