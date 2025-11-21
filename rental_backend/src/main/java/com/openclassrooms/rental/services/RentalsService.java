package com.openclassrooms.rental.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.RentalsCreateDTO;
import com.openclassrooms.rental.dto.RentalsDTO;
import com.openclassrooms.rental.model.Rentals;
import com.openclassrooms.rental.repository.RentalRepository;

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
        Rentals rental = new Rentals(
            null,
            dto.getName(),
            dto.getSurface(),
            dto.getPrice(),
            dto.getDescription(),
            dto.getPicture(),
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
        if (dto.getPicture() != null) {
            rental.setPicture(dto.getPicture());
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
