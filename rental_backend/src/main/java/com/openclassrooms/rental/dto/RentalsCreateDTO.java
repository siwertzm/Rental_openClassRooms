package com.openclassrooms.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalsCreateDTO {
    private String name;
    private Integer surface;
    private Integer price;
    private String description;
    private String picture;
}
