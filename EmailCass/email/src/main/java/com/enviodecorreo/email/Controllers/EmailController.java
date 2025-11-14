package com.enviodecorreo.email.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviodecorreo.email.Entity.Email;
import com.enviodecorreo.email.services.EmailService;
import com.enviodecorreo.email.Repository.EmailRepository;
import com.enviodecorreo.email.Entity.Email;

@RestController
@RequestMapping("/api/email")
public class EmailController { 

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody Email email) {
        emailService.sendEmail(email);
        return ResponseEntity.ok("Correo enviado correctamente");
    }

    @GetMapping
    public ResponseEntity<java.util.List<Email>> getAll() {
        return ResponseEntity.ok(emailRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<java.util.Optional<Email>> getById(@PathVariable String id) {
        return ResponseEntity.ok(emailRepository.findById(id));
    }
}
