package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkoutDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "splitId")
    private WorkoutSplit workoutSplit;
    private DayOfWeek dayOfWeek;
    private String label;
    private int sequenceNo;
    @OneToMany(mappedBy = "workoutDay")
    private List<WorkoutDayExercise> workoutDayExercises;

}
