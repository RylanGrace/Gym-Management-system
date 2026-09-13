package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate effectiveTo;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

 public  enum ScheduleStatus {
        ACTIVE,
        FUTURE,
        ENDED
    }

}

