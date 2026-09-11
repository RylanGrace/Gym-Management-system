package com.project.gym.repository;

import com.project.gym.model.WorkoutSplit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSplitRepo extends JpaRepository<WorkoutSplit,Integer> {
}
