package com.compra.compra.enums;

import com.fasterxml.jackson.annotation.*;

public enum StatusTransaccion {

    @JsonProperty("exitosa")
    EXITOSA,
    @JsonProperty("rechazada")
    RECHAZADA,
    @JsonProperty("anulada")
    ANULADA,
    @JsonProperty("pendiente")
    PENDIENTE
    
}
