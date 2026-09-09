package com.project.gym.DTO;

import com.project.gym.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResponseDTO {
    private int id;
    private String name;
    private Gender gender;
}
