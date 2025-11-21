package com.openclassrooms.rental.dto;

import lombok.Data;

@Data
public class MessagesCreateDTO {
    private String message;
    private Long rental_id;
    private Long user_id;
}
