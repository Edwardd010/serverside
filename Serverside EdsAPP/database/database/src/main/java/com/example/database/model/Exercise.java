package com.example.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "exercise_name")
    private String exerciseName;
    @Column(name = "sets")
    private int sets;
    @Column(name = "reps")
    private int reps;
    @Column(name = "weight")
    private int weight;
    public Long getId() {
        return id;
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
