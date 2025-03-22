package org.example.bank.controller;

import org.example.bank.dto.LoginRequest;
import org.example.bank.service.KlientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KlientService klientService;

    public AuthController(KlientService klientService) {
        this.klientService = klientService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = klientService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}