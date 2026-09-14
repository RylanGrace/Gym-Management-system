package com.project.gym.repository;

import com.project.gym.model.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrainingScheduleRepo extends JpaRepository<TrainingSchedule, Integer> {

    List<TrainingSchedule> findByStatusAndEffectiveFormLessThanEqual(
            TrainingSchedule.ScheduleStatus status,
            LocalDate date);

    List<TrainingSchedule> findByStatusAndEffectiveToLessThan(
            TrainingSchedule.ScheduleStatus status,
            LocalDate date);

    List<TrainingSchedule> findByMemberId_Id(int memberId);

    List<TrainingSchedule> findByTrainerId_Id(int trainerId);
}
