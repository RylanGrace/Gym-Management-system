package com.project.gym.DTO;

import com.project.gym.model.TrainingSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingScheduleResponseDTO {

    private int scheduleId;
    private int memberId;
    private int trainerId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate effectiveForm;
    private LocalDate effectiveTo;
    private TrainingSchedule.ScheduleStatus status;

}
