package com.openclassrooms.rental.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.RentalsCreateDTO;
import com.openclassrooms.rental.dto.RentalsDTO;
import com.openclassrooms.rental.model.Rentals;
import com.openclassrooms.rental.repository.RentalRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalsService {

    private final RentalRepository rentalRepository;

    public List<RentalsDTO> findAll(){
        return rentalRepository.findAll().stream().map(this::toDTO).toList();
    }

    public RentalsDTO findById(Long id) {
        Rentals rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
        return toDTO(rental);
    }

    public RentalsDTO create(RentalsCreateDTO dto, Long ownerId) {
        String picturePath = null;

    if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {

        // Sécurité 
        if (!dto.getPicture().getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        try {
            // Nom de fichier safe
            String extension = Optional.ofNullable(dto.getPicture().getOriginalFilename())
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(f.lastIndexOf(".")))
                    .orElse(".jpg");

            String filename = UUID.randomUUID() + extension;

            Path uploadDir = Paths.get("uploads");
            Files.createDirectories(uploadDir);

            Path target = uploadDir.resolve(filename);

            //Écrasement interdit
            Files.copy(
                dto.getPicture().getInputStream(),
                target,
                StandardCopyOption.REPLACE_EXISTING
            );

            picturePath = "http://localhost:8080/uploads/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
        Rentals rental = new Rentals(
            null,
            dto.getName(),
            dto.getSurface(),
            dto.getPrice(),
            dto.getDescription(),
            picturePath,
            ownerId
        );
        rentalRepository.save(rental);
        return toDTO(rental);
    }

    public RentalsDTO update(Long id, RentalsCreateDTO dto) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (dto.getName() != null) {
            rental.setName(dto.getName());
        }
        if (dto.getSurface() != null) {
            rental.setSurface(dto.getSurface());
        }
        if (dto.getPrice() != null) {
            rental.setPrice(dto.getPrice());
        }
        if (dto.getDescription() != null) {
            rental.setDescription(dto.getDescription());
        }

        return toDTO(rentalRepository.save(rental));
    }

    private RentalsDTO toDTO(Rentals rental) {
        return new RentalsDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getDescription(),
                rental.getPicture(),
                rental.getOwnerId()
        );
    }
    
}
