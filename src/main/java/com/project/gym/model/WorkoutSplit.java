package com.project.gym.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WorkoutSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "workoutSplit")
    private List<WorkoutDay> workoutDays;

}
