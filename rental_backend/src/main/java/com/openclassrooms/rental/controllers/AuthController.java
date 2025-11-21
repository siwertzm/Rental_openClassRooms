package com.openclassrooms.rental.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.rental.dto.UserDTO;
import com.openclassrooms.rental.model.User;
import com.openclassrooms.rental.repository.UserRepository;
import com.openclassrooms.rental.services.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok().body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existing = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(existing.getEmail());
        return ResponseEntity.ok().body(new AuthResponse(token));
    }

    @GetMapping("/me")
    @Operation(summary = "Récupère les informations de l'utilisateur connecté")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Utilisateur authentifié"),
        @ApiResponse(responseCode = "401", description = "Token invalide ou manquant")
    })
    public ResponseEntity<UserDTO> getCurrentUser() {

        String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(new UserDTO(user));
    }
    
    record AuthResponse(String token) {}
}
