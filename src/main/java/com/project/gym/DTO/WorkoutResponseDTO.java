package com.project.gym.DTO;

import com.project.gym.model.WorkoutAssignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponseDTO {

    private int assignmentId;
    private int memberId;
    private int workoutSplitId;
    private String workoutSplitName;
    private int trainerId;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    private WorkoutAssignment.AssignmentStatus status;
}
