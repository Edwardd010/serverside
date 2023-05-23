package com.example.database.controller;

import com.example.database.dto.ExerciseDTO;
import com.example.database.dto.WorkoutDTO;
import com.example.database.service.WorkoutService;
import jakarta.validation.Valid;
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
@RequestMapping("workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service){
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<Object> workout(@Valid @RequestBody WorkoutDTO wdto, BindingResult br){
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        Long id = service.createWorkout(wdto);
        wdto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(wdto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDTO> getWorkout(@PathVariable Long id) {
        WorkoutDTO wdto = service.getWorkout(id);
        return ResponseEntity.ok(wdto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public ResponseEntity<List<WorkoutDTO>> getWorkouts() {
        Iterable<WorkoutDTO> workoutsIterable = service.getWorkouts();
        List<WorkoutDTO> workoutsList = new ArrayList<>();
        for (WorkoutDTO workout : workoutsIterable) {
            workoutsList.add(workout);
        }
        return ResponseEntity.ok(workoutsList);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeWorkout(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Workout " + id + " got removed!");
    }
}

