package com.project.gym.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class WorkoutAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Member memberId;
    @ManyToOne
    private WorkoutSplit splitId;
    @ManyToOne
    private Trainer assignedByTrainerId;
    private LocalDateTime assignedAt;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
}


enum  AssignmentStatus {
    ACTIVE,
    SCHEDULED,
    ENDED;
}