package com.openclassrooms.rental.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.RentalsCreateDTO;
import com.openclassrooms.rental.dto.RentalsDTO;
import com.openclassrooms.rental.model.User;
import com.openclassrooms.rental.repository.UserRepository;
import com.openclassrooms.rental.services.RentalsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalsController {

    private final RentalsService rentalService;
    private final UserRepository userRepository;

    @GetMapping
    public List<RentalsDTO> getAll() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    public RentalsDTO getById(@PathVariable Long id) {
        return rentalService.findById(id);
    }

    @PostMapping
    public RentalsDTO create(@RequestBody RentalsCreateDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        return rentalService.create(dto, user.getId());
    }

    @PutMapping("/{id}")
    public RentalsDTO update(@PathVariable Long id, @RequestBody RentalsCreateDTO dto) {
        return rentalService.update(id, dto);
    }
}
