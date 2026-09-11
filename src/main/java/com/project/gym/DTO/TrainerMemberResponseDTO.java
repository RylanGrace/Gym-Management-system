package com.project.gym.DTO;

import com.project.gym.model.Membership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerMemberResponseDTO {

    private int membershipId;
    private int memberId;
    private String memberName;
    private String phoneNo;
    private Membership.MembershipStatus status;
    private LocalDate applicationDate;
    private LocalDateTime managerApprovedAt;

}
