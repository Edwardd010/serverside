package com.example.database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public class ExerciseDTO {
    public Long id;

    @Size(min = 2, max = 20, message = "length must be between 2 and 20 characters")
    public String exerciseName;


    public int sets;


    public int reps;


    public int weight;
}
