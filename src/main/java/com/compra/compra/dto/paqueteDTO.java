package com.compra.compra.dto;

import com.compra.compra.entity.EstadoValidacionOTN;

public record paqueteDTO(String codigo, String destino, Double precio, EstadoValidacionOTN estad) {

   }

