package com.project.gym.repository;

import com.project.gym.model.WorkoutDayExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutDayExerciseRepo  extends JpaRepository<WorkoutDayExercise, Integer> {
    List<WorkoutDayExercise> findByWorkoutDay_Id(int workoutDayId);
}
