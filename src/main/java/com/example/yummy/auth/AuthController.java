package com.example.yummy.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.yummy.dto.LoginRequest;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/auth")

public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String result = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(result);
    }
}