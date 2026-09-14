package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDayResponseDTO {
    private int workoutDayId;
    private DayOfWeek dayOfWeek;
    private String label;
    private int sequenceNo;
    private List<ExerciseResponseDTO> exercises;
}
