package com.project.gym.DTO;

import com.project.gym.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private int id;
    private String email;
    private User.UserRole role;

}
