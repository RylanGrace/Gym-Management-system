package com.project.gym.controller;

import com.project.gym.DTO.*;
import com.project.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private TrainingScheduleService trainingScheduleService;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/{memberId}/workout")
    public WorkoutResponseDTO getWorkout(@PathVariable int memberId) {
        return workoutService.getWorkout(memberId);
    }

    @GetMapping("/{memberId}/schedule")
    public List<TrainingScheduleResponseDTO> getSchedule(
            @PathVariable int memberId) {

        return  trainingScheduleService.getMemberSchedule(memberId);
    }

    @GetMapping("/{memberId}/measurements")
    public List<MeasurementResponseDTO> getMeasurements(
            @PathVariable int memberId) {

        return measurementService.getMemberMeasurements(memberId);
    }

    @PostMapping("/{memberId}/attendance")
    public void recordAttendance(@PathVariable int memberId) {
        attendanceService.recordAttendance(memberId);
    }

    @GetMapping("/{memberId}/attendance")
    public List<AttendanceResponseDTO> getAttendance(
            @PathVariable int memberId) {

        return attendanceService.getMemberAttendance(memberId);
    }

    @GetMapping("/{memberId}/workout/details")
    public List<WorkoutDayResponseDTO> getWorkoutDetails(
            @PathVariable int memberId) {

        return workoutService.getWorkoutDays(memberId);
    }

    @GetMapping("/{memberId}/memberships")
    public List<MembershipResponseDTO> getMemberships(
            @PathVariable int memberId) {

        return membershipService.getMemberMemberships(memberId);
    }
}
