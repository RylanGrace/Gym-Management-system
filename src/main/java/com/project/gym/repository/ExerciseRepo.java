package com.project.gym.repository;

import com.project.gym.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise, Integer> {
}
