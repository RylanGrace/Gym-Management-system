package com.project.gym.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TrainingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Member memberId;
    @ManyToOne
    private Trainer trainerId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate effectiveForm;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
}

enum ScheduleStatus {
    ACTIVE,
    FUTURE,
    ENDED
}
