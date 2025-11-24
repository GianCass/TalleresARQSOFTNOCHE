package com.proyecto.authservice.user.dto;

public record UserMeResponse(
        String id,
        String nombreCompleto,
        String email,
        String role
) {}
