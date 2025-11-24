package com.proyecto.authservice.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;   // Cedula

    private String nombreCompleto;

    @Indexed(unique = true)
    private String email;

    private String password;
    private Role role;
}
