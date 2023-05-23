package com.example.database.model;

import com.example.database.dto.ExerciseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workoutName;
    private String workoutDate;
    public Long getId() {
        return id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(String workoutDate) {
        this.workoutDate = workoutDate;
    }


}
