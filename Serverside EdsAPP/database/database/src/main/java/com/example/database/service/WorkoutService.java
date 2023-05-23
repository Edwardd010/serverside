package com.example.database.service;

import com.example.database.dto.ExerciseDTO;
import com.example.database.dto.WorkoutDTO;
import com.example.database.exception.ResourceNotFoundException;
import com.example.database.model.Exercise;
import com.example.database.model.Workout;
import com.example.database.repository.ExerciseRepository;
import com.example.database.repository.WorkoutRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WorkoutService {

    private final WorkoutRepository repos;

    private ExerciseService exerciseService;

    public WorkoutService(WorkoutRepository repos) {
        this.repos = repos;
    }

    public Long createWorkout(WorkoutDTO wdto){
        Workout w = new Workout();
        w.setWorkoutName(wdto.workoutName);
        w.setWorkoutDate(wdto.workoutDate);

        repos.save(w);


        return w.getId();
    }

    public List<WorkoutDTO> getWorkouts() {
        Iterable<Workout> workouts = repos.findAll();
        List<WorkoutDTO> workoutsDTO = new ArrayList<>();
        for (Workout workout : workouts) {
            WorkoutDTO wdto = new WorkoutDTO();
            wdto.id = workout.getId();
            wdto.workoutName = workout.getWorkoutName();
            wdto.workoutDate = workout.getWorkoutDate();

            workoutsDTO.add(wdto);
        }
        return workoutsDTO;
    }

    public WorkoutDTO getWorkout(Long id) {
        Workout w = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

        WorkoutDTO wdto = new WorkoutDTO();
        wdto.id = w.getId();
        wdto.workoutName = w.getWorkoutName();
        wdto.workoutDate = w.getWorkoutDate();


        return wdto;
    }

    public void delete(Long id) {
        repos.deleteById(id);
    }
}
