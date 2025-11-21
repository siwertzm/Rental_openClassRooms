package com.openclassrooms.rental.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.MessagesCreateDTO;
import com.openclassrooms.rental.dto.MessagesResponseDTO;
import com.openclassrooms.rental.services.MessagesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    @PostMapping
    public ResponseEntity<MessagesResponseDTO> sendMessage(@RequestBody MessagesCreateDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(dto);
        return ResponseEntity.ok(messagesService.create(dto, email));
    }
}