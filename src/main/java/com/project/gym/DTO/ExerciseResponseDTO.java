package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDTO {
    private int exerciseId;
    private String name;
    private String description;
    private int sequenceNo;
}
