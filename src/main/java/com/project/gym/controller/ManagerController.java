package com.project.gym.controller;

import com.project.gym.DTO.*;
import com.project.gym.model.Membership;
import com.project.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private MembershipService service;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentIssueService equipmentIssueService;

    @Autowired
    private SalaryPaymentService salaryPaymentService;

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/memberships/scheduled")
    public List<ScheduledMembershipResponseDTO> getScheduled(){
        return service.getScheduledMemberships();
    }

    @PostMapping("/memberships/{membershipId}/verify")
    public void verifyMembership(@PathVariable int membershipId, @RequestBody AssignTrainerDTO request){
        service.managerApprove(membershipId,request.getTrainerId());
    }

    @PostMapping("/equipment")
    public void addEquipment(@RequestBody EquipmentRequestDTO request) {
        equipmentService.addEquipment(request);
    }

    @GetMapping("/equipment")
    public List<EquipmentResponseDTO> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PutMapping("/equipment-issues/{issueId}/schedule")
    public void scheduleService(
            @PathVariable int issueId,
            @RequestBody EquipmentIssueScheduleDTO request) {

        equipmentIssueService.scheduleService(issueId, request);
    }

    @PutMapping("/equipment-issues/{issueId}/solve")
    public void solveIssue(
            @PathVariable int issueId,
            @RequestBody EquipmentIssueSolveDTO request) {

        equipmentIssueService.solveIssue(issueId, request);
    }

    @GetMapping("/equipment-issues")
    public List<EquipmentIssueResponseDTO> getAllIssues() {
        return equipmentIssueService.getAllIssues();
    }

    @PostMapping("/salary-payments")
    public void recordSalaryPayment(
            @PathVariable int managerId,
            @RequestBody SalaryPaymentRequestDTO request) {

        salaryPaymentService.recordSalaryPayment(managerId, request);
    }

    @GetMapping("/salary-payments")
    public List<SalaryPaymentResponseDTO> getSalaryHistory() {
        return salaryPaymentService.getSalaryHistory();
    }

    @GetMapping("/trainers")
    public List<TrainerResponseDTO> getAllTrainers() {
        return trainerService.getAllTrainers();
    }
}
