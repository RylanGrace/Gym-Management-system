package com.project.gym.repository;

import com.project.gym.model.Member;
import com.project.gym.model.WorkoutAssignment;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutAssignmentRepo extends JpaRepository<WorkoutAssignment, Integer> {

    WorkoutAssignment findByMemberId_Id(int memberId);

    WorkoutAssignment findByMemberId_IdAndStatus(
            int memberId,
            WorkoutAssignment.AssignmentStatus status
    );

    List<WorkoutAssignment> findByStatusAndEffectiveFromLessThanEqual(WorkoutAssignment.AssignmentStatus status, LocalDate date );

    List<WorkoutAssignment> findByStatusAndEffectiveToLessThan(WorkoutAssignment.AssignmentStatus status, LocalDate date );

}
