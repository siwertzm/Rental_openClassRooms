package com.openclassrooms.rental.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.openclassrooms.rental.dto.MessagesCreateDTO;
import com.openclassrooms.rental.dto.MessagesResponseDTO;
import com.openclassrooms.rental.model.Messages;
import com.openclassrooms.rental.model.User;
import com.openclassrooms.rental.repository.MessagesRepository;
import com.openclassrooms.rental.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final UserRepository userRepository;

    public MessagesResponseDTO create(MessagesCreateDTO dto, String email) {
        // Récupérer l'utilisateur authentifié
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Messages msg = new Messages();
        msg.setMessage(dto.getMessage());
        msg.setRental_id(dto.getRental_id());
        msg.setUser_id(user.getId());
        msg.setCreatedAt(LocalDateTime.now());

        messagesRepository.save(msg);

        return new MessagesResponseDTO("Message envoyé avec succès");
    }
}
