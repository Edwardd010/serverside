package com.example.database.controller;


import com.example.database.dto.ExerciseDTO;
import com.example.database.dto.UserDTO;
import com.example.database.model.Exercise;
import com.example.database.model.LoginRequest;
import com.example.database.model.User;
import com.example.database.service.ExerciseService;
import com.example.database.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("exercises")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<Object> exercise(@Valid @RequestBody ExerciseDTO edto, BindingResult br){
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        Long id = service.createExercise(edto);
        edto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(edto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExercise(@PathVariable Long id) {
        ExerciseDTO edto = service.getExercise(id);
        return ResponseEntity.ok(edto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public ResponseEntity<List<ExerciseDTO>> getExercises() {
        Iterable<ExerciseDTO> exercisesIterable = service.getExercises();
        List<ExerciseDTO> exercisesList = new ArrayList<>();
        for (ExerciseDTO exercise : exercisesIterable) {
            exercisesList.add(exercise);
        }
        return ResponseEntity.ok(exercisesList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeExercise(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Exercise " + id + " got removed!");
    }
}

