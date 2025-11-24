package com.proyecto.authservice.user;

import com.proyecto.authservice.user.dto.UserMeResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserMeResponse getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return null; // SecurityConfig + filtro JWT ya bloquean antes
        }
        String email = authentication.getName();

        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        return new UserMeResponse(
                user.getId(),
                user.getNombreCompleto(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
