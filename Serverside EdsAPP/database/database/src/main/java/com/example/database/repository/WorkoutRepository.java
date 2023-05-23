package com.example.database.repository;

import com.example.database.model.Workout;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    Workout findByWorkoutName(String workoutName);
}
