package com.example.database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public class UserDTO {
    public Long id;
    @NotBlank
    @Size(min = 2, max = 12, message = "length must be between 2 and 12 characters")
    public String username;

    @NotBlank
    @Size(min = 2, max = 12, message = "length must be between 2 and 12 characters")
    public String password;

}
