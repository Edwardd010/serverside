package com.example.database.service;
import com.example.database.dto.ExerciseDTO;
import com.example.database.exception.ResourceNotFoundException;
import com.example.database.model.Exercise;
import com.example.database.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExerciseService {

    private final ExerciseRepository repos;

    public ExerciseService(ExerciseRepository repos){
        this.repos = repos;
    }

    public AtomicLong counter = new AtomicLong(0);

    public Long generateWorkoutId() {
        return counter.incrementAndGet();
    }

    public Long createExercise(ExerciseDTO edto){
        Exercise e = new Exercise();
        e.setExerciseName(edto.exerciseName);
        e.setSets(edto.sets);
        e.setReps(edto.reps);
        e.setWeight(edto.weight);
        e.setWorkoutId(edto.workoutId);
        repos.save(e);

        return e.getId();
    }


    public ExerciseDTO getExercise(Long id){
        Exercise e = repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Exercise not found"));

        // mapping
        ExerciseDTO edto = new ExerciseDTO();
        edto.id = e.getId();
        edto.exerciseName = e.getExerciseName();
        edto.sets = e.getSets();
        edto.reps = e.getReps();
        edto.weight = e.getWeight();
        edto.workoutId = e.getWorkoutId();

        return edto;
    }

    public List<ExerciseDTO> getExercises(){
        Iterable<Exercise> exercises = repos.findAll();
        List<ExerciseDTO> exercisesDTO = new ArrayList<>();
        for(Exercise exercise : exercises){
            ExerciseDTO edto = new ExerciseDTO();
            edto.id = exercise.getId();
            edto.exerciseName = exercise.getExerciseName();
            edto.sets = exercise.getSets();
            edto.reps = exercise.getReps();
            edto.weight = exercise.getWeight();
            edto.workoutId = exercise.getWorkoutId();
            exercisesDTO.add(edto);
        }
        return exercisesDTO;
    }

    public void delete(Long id) {
        repos.deleteById(id);
    }

}

