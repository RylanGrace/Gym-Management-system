package com.project.gym.DTO;

import com.project.gym.model.Membership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDTO {


    private int membershipId;
    private int memberId;

    private int planId;
    private String planName;
    private int duration;
    private BigDecimal price;

    private Membership.MembershipStatus status;

    private LocalDate applicationDate;
    private LocalDateTime managerApprovedAt;

    private int trainerId;
    private String trainerName;
    private LocalDateTime trainerApprovedAt;

    private LocalDate joiningDate;
    private LocalDate expiryDate;
}
