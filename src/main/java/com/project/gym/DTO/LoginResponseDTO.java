package com.project.gym.DTO;

import com.project.gym.model.MembershipPlan;
import com.project.gym.model.Trainer;
import com.project.gym.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String message;
    private String role;

    private MemberResponseDTO memberResponseDTO;
    private TrainerResponseDTO trainerResponseDTO;
    private UserResponseDTO userResponseDTO;
    private List<MembershipPlanDTO> plans;
}
