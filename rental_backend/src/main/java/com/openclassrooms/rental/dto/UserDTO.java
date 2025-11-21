package com.openclassrooms.rental.dto;

import com.openclassrooms.rental.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
