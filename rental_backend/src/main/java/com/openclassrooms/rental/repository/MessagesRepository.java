package com.openclassrooms.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.rental.model.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Long>  {
    
}
