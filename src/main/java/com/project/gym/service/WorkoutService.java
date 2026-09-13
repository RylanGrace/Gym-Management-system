package com.project.gym.service;


import com.project.gym.DTO.WorkoutResponseDTO;
import com.project.gym.DTO.WorkoutSplitChangeDTO;
import com.project.gym.model.Membership;
import com.project.gym.model.WorkoutAssignment;
import com.project.gym.model.WorkoutSplit;
import com.project.gym.repository.MembershipRepo;
import com.project.gym.repository.WorkoutAssignmentRepo;
import com.project.gym.repository.WorkoutSplitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutAssignmentRepo workoutAssignmentRepo;

    @Autowired
    private MembershipRepo membershipRepo;

    @Autowired
    private WorkoutSplitRepo workoutSplitRepo;

    public WorkoutResponseDTO getWorkout(int memberId){
        WorkoutAssignment assignment = workoutAssignmentRepo.findByMemberId_Id(memberId);

        WorkoutResponseDTO responseDTO = new WorkoutResponseDTO();

        responseDTO.setAssignmentId(assignment.getId());
        responseDTO.setMemberId(assignment.getMemberId().getId());
        responseDTO.setWorkoutSplitId(assignment.getSplitId().getId());
        responseDTO.setWorkoutSplitName(assignment.getSplitId().getName());
        responseDTO.setTrainerId(assignment.getAssignedByTrainerId().getId());
        responseDTO.setEffectiveFrom(assignment.getEffectiveFrom());
        responseDTO.setEffectiveTo(assignment.getEffectiveTo());
        responseDTO.setStatus(assignment.getStatus());

        return responseDTO;
    }

    public void changeWorkoutSplit(int membershipId, int trainerId, WorkoutSplitChangeDTO request){

        Membership membership = membershipRepo.findById(membershipId).orElseThrow(() -> new RuntimeException("Membership not found"));

        if(membership.getTrainer() == null || membership.getTrainer().getId() != trainerId){
            throw new RuntimeException("Trainer is not assigned to this membership");
        }

        WorkoutSplit split = workoutSplitRepo.findById(request.getWorkoutSplitId())
                .orElseThrow(() -> new RuntimeException("Workout split not found"));

        LocalDate effectiveFrom = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        WorkoutAssignment currentAssignment =
                workoutAssignmentRepo.findByMemberId_Id(membership.getMember().getId());

        if (currentAssignment != null &&
                currentAssignment.getStatus() == WorkoutAssignment.AssignmentStatus.SCHEDULED) {

            currentAssignment.setSplitId(split);
            currentAssignment.setAssignedByTrainerId(membership.getTrainer());
            currentAssignment.setAssignedAt(LocalDateTime.now());

            workoutAssignmentRepo.save(currentAssignment);

            return;
        }

        WorkoutAssignment assignment = new WorkoutAssignment();

        assignment.setMemberId(membership.getMember());
        assignment.setSplitId(split);
        assignment.setAssignedByTrainerId(membership.getTrainer());
        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setEffectiveFrom(effectiveFrom);
        assignment.setEffectiveTo(membership.getExpiryDate());
        assignment.setStatus(WorkoutAssignment.AssignmentStatus.SCHEDULED);

        WorkoutAssignment oldAssignment =
                workoutAssignmentRepo.findByMemberIdAndStatus(membership.getMember().getId(), WorkoutAssignment.AssignmentStatus.ACTIVE);

        if (oldAssignment != null) {
            oldAssignment.setEffectiveTo(effectiveFrom.minusDays(1));
            oldAssignment.setStatus(WorkoutAssignment.AssignmentStatus.ENDED);
            workoutAssignmentRepo.save(oldAssignment);
        }
        workoutAssignmentRepo.save(assignment);

    }


}
