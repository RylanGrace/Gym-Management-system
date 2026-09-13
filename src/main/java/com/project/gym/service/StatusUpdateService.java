package com.project.gym.service;

import com.project.gym.model.TrainingSchedule;
import com.project.gym.model.WorkoutAssignment;
import com.project.gym.repository.TrainingScheduleRepo;
import com.project.gym.repository.WorkoutAssignmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatusUpdateService {

    @Autowired
    private WorkoutAssignmentRepo workoutAssignmentRepo;

    @Autowired
    private TrainingScheduleRepo trainingScheduleRepo;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateWorkoutAssignmentStatuses(){

        LocalDate today = LocalDate.now();

        List<WorkoutAssignment> scheduledAssignments = workoutAssignmentRepo.findByStatusAndEffectiveFromLessThanEqual(WorkoutAssignment.AssignmentStatus.SCHEDULED, today);

        for(WorkoutAssignment assignment : scheduledAssignments){
            assignment.setStatus(WorkoutAssignment.AssignmentStatus.ACTIVE);
        }

        List<WorkoutAssignment> activeAssignments = workoutAssignmentRepo.findByStatusAndEffectiveToLessThan(WorkoutAssignment.AssignmentStatus.ACTIVE,today);

        for(WorkoutAssignment assignment : activeAssignments){
            assignment.setStatus(WorkoutAssignment.AssignmentStatus.ENDED);
        }

        workoutAssignmentRepo.saveAll(scheduledAssignments);
        workoutAssignmentRepo.saveAll(activeAssignments);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTrainingScheduleStatuses() {

        LocalDate today = LocalDate.now();

        List<TrainingSchedule> futureSchedules =
                trainingScheduleRepo.findByStatusAndEffectiveFromLessThanEqual(
                        TrainingSchedule.ScheduleStatus.FUTURE,
                        today);

        for (TrainingSchedule schedule : futureSchedules) {
            schedule.setStatus(TrainingSchedule.ScheduleStatus.ACTIVE);
        }

        List<TrainingSchedule> activeSchedules =
                trainingScheduleRepo.findByStatusAndEffectiveToLessThan(
                        TrainingSchedule.ScheduleStatus.ACTIVE,
                        today);

        for (TrainingSchedule schedule : activeSchedules) {
            schedule.setStatus(TrainingSchedule.ScheduleStatus.ENDED);
        }

        trainingScheduleRepo.saveAll(futureSchedules);
        trainingScheduleRepo.saveAll(activeSchedules);
    }
}
