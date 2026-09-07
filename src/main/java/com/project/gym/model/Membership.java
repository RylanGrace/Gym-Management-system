package com.project.gym.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private  Member member;
    @ManyToOne
    @JoinColumn(name = "planId")
    private MembershipPlan membershipPlan;
    @Enumerated(EnumType.STRING)
    private MembershipStatus status;
    private LocalDate applicationDate;
    private LocalDateTime managerApprovedAt;
    @ManyToOne
    @JoinColumn(name = "trainerId")
    private Trainer trainer;
    private LocalDateTime trainerApprovedAt;
    private LocalDate joiningDate;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;
}

enum MembershipStatus {
    SCHEDULED,
    ACTIVE,
    EXPIRE
}