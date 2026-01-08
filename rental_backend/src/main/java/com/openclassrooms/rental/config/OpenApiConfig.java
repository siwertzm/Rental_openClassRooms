package com.openclassrooms.rental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rentalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rental API")
                        .description("API de l’application de location (OpenClassrooms)")
                        .version("v1.0"));
    }
}
