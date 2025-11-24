package com.proyecto.authservice.auth;

import com.proyecto.authservice.auth.dto.AuthRequest;
import com.proyecto.authservice.auth.dto.AuthResponse;
import com.proyecto.authservice.auth.dto.RegisterRequest;
import com.proyecto.authservice.exception.BadRequestException;
import com.proyecto.authservice.exception.NotFoundException;
import com.proyecto.authservice.exception.UnauthorizedException;
import com.proyecto.authservice.security.JwtService;
import com.proyecto.authservice.user.Role;
import com.proyecto.authservice.user.User;
import com.proyecto.authservice.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        String email = request.email().trim().toLowerCase();
        String id = request.id().trim();

        if (id.isEmpty()) {
            throw new BadRequestException("La cédula (id) es obligatoria");
        }

        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("El email ya está registrado");
        }

        User user = User.builder()
                .id(id)
                .nombreCompleto(request.nombreCompleto().trim())
                .email(email)
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {

        String email = request.email().trim().toLowerCase();

        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException("No existe un usuario registrado con ese correo"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("La contraseña es incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
