package com.openclassrooms.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsDTO {
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    private String description;
    private String picture;
    private Long ownerId;
}
