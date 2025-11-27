package com.compra.compra.enums;

import com.fasterxml.jackson.annotation.*;

public enum TipoTarjeta {

    @JsonProperty("credito")
    CREDITO,
    @JsonProperty("debito")
    DEBITO
}
