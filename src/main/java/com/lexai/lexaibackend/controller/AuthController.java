package com.lexai.lexaibackend.controller;

import com.lexai.lexaibackend.model.User;
import com.lexai.lexaibackend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Authentication", description="Register and login endpoints")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary="Register a new user", description = "Creates a new Account and Bcrypt hash password")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = authService.register(user);
        return ResponseEntity.ok(saved);
    }
    @Operation(summary ="Login and JWT token", description="Validates credentials and returns a signed JWT token valid for 1 hour")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String token = authService.login(email, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}