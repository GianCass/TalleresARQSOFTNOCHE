package com.proyecto.authservice.user;

import com.proyecto.authservice.user.dto.UserMeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CurrentUserService currentUserService;

    public UserController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserMeResponse> me() {
        var me = currentUserService.getCurrentUser();
        if (me == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(me);
    }
}
