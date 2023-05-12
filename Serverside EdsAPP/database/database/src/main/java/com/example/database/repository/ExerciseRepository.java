package com.example.database.repository;

import com.example.database.model.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    Exercise findByExerciseName(String exerciseName);

}
