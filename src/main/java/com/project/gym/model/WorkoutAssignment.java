package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

  public  enum  AssignmentStatus {
        ACTIVE,
        SCHEDULED,
        ENDED;
    }
}


