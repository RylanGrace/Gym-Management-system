package com.project.gym.DTO;

import com.project.gym.model.Membership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledMembershipResponseDTO {

    private int membershipId;
    private int memberId;
    private String memberName;
    private String phoneNo;

    private int planId;
    private String planName;
    private int duration;

    private LocalDate applicationDate;
    private Membership.MembershipStatus status;
}
