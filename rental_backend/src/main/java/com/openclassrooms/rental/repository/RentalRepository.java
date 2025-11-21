package com.openclassrooms.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.rental.model.Rentals;

public interface RentalRepository extends JpaRepository<Rentals, Long> {
    
}
