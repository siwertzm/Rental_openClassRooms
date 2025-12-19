package com.openclassrooms.rental.dto;

import java.util.List;

public class RentalsResponseDTO {

    private List<RentalsDTO> rentals;

    public RentalsResponseDTO(List<RentalsDTO> rentals) {
        this.rentals = rentals;
    }

    public List<RentalsDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalsDTO> rentals) {
        this.rentals = rentals;
    }
}