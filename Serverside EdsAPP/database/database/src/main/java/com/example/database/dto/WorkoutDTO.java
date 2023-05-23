package com.example.database.dto;

import com.example.database.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
public class WorkoutDTO {

    public Long id;

    public String workoutName;

    public String workoutDate;

    public List<ExerciseDTO> exercises;

    public List<ExerciseDTO> getExercises(){
        return exercises;
    }
     public void setExercises (List<ExerciseDTO> exercises){
        this.exercises = exercises;
     }


}
