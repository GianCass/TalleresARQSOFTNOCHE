package com.proyecto.authservice.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String id,                   // cedula
        @NotBlank String nombreCompleto,
        @Email @NotBlank String email,
        @NotBlank @Size(min = 6) String password
) {}
