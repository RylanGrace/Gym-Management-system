package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkoutDayExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "workoutDayId")
    private WorkoutDay workoutDay;
    @ManyToOne
    private Exercise exerciseId;
    private int sequenceNo;
}
