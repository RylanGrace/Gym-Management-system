package com.project.gym.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class EquipmentIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Equipment equipmentId;
    @ManyToOne
    private Trainer reportedByTrainerId;
    private String issueDescription;
    private LocalDateTime reportedAt;
    private LocalDate serviceScheduledDate;
    private String servicePersonName;
    private String servicePersonContact;
    private LocalDate resolvedDate;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    private String remarks;
}

enum IssueStatus{
    OPEN,
    SCHEDULED,
    SOLVED;
}