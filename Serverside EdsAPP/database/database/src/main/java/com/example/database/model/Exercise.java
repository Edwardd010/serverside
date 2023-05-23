package com.example.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exercise_name")
    private String exerciseName;
    @Column(name = "sets")
    private int sets;
    @Column(name = "reps")
    private int reps;
    @Column(name = "weight")
    private int weight;

    @Column(name = "workout_id")
    private Long workoutId;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setExerciseName(String exerciseName){
        this.exerciseName = exerciseName;
    }
    public void setSets(int sets){
        this.sets = sets;
    }
    public void setReps(int reps){
        this.reps = reps;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getExerciseName(){
        return exerciseName;
    }
    public int getSets(){
        return sets;
    }
    public int getReps(){
        return reps;
    }
    public int getWeight(){
        return weight;
    }


}
