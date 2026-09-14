package com.project.gym.controller;

import com.project.gym.DTO.*;
import com.project.gym.service.EquipmentIssueService;
import com.project.gym.service.MeasurementService;
import com.project.gym.service.MembershipService;
import com.project.gym.service.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private MembershipService service;

    @Autowired
    private TrainingScheduleService trainingScheduleService;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private EquipmentIssueService equipmentIssueService;

    @GetMapping("/{trainerId}/verified")
    public List<TrainerMemberResponseDTO> getVerifiedMembership(@PathVariable int trainerId){
        return service.getVerifiedMembership(trainerId);
    }

    @PostMapping("/{trainerId}/memberships/{membershipId}/setup")
    public void setupTrainer(
            @PathVariable int trainerId,
            @PathVariable int membershipId,
            @RequestBody TrainerSetupRequestDTO request) {

        service.setupTrainer(membershipId, trainerId, request);
    }

    @GetMapping("/{trainerId}/schedule")
    public List<TrainingScheduleResponseDTO> getSchedules(
            @PathVariable int trainerId) {

        return trainingScheduleService.getTrainerSchedules(trainerId);
    }

    @PostMapping("/{trainerId}/members/{memberId}/measurements")
    public void recordMeasurement(
            @PathVariable int trainerId,
            @PathVariable int memberId,
            @RequestBody MeasurementRequestDTO request) {

        measurementService.recordMeasurement(
                memberId, trainerId, request);
    }

    @PostMapping("/{trainerId}/equipment-issues")
    public void reportIssue(
            @PathVariable int trainerId,
            @RequestBody EquipmentIssueRequestDTO request) {

        equipmentIssueService.reportIssue(trainerId, request);
    }

    @GetMapping("/{trainerId}/active")
    public List<TrainerMemberResponseDTO> getActiveMembers(
            @PathVariable int trainerId) {

        return service.getActiveMembers(trainerId);
    }
}
