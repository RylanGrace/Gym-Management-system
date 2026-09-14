package com.project.gym.repository;

import com.project.gym.model.WorkoutDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutDayRepo extends JpaRepository<WorkoutDay, Integer> {

    List<WorkoutDay> findByWorkoutSplit_Id(int workoutSplitId);
}
