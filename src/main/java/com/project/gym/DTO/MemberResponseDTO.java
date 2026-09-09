package com.project.gym.DTO;

import com.project.gym.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {


        private int id;
        private String name;
        private Gender gender;
        private String phoneNo;

        // getters and setters
}
