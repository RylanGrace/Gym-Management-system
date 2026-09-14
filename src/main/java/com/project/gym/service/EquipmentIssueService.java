package com.project.gym.service;

import com.project.gym.DTO.EquipmentIssueRequestDTO;
import com.project.gym.DTO.EquipmentIssueResponseDTO;
import com.project.gym.DTO.EquipmentIssueScheduleDTO;
import com.project.gym.DTO.EquipmentIssueSolveDTO;
import com.project.gym.model.Equipment;
import com.project.gym.model.EquipmentIssue;
import com.project.gym.model.Trainer;
import com.project.gym.repository.EquipmentIssueRepo;
import com.project.gym.repository.EquipmentRepo;
import com.project.gym.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentIssueService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private EquipmentIssueRepo equipmentIssueRepo;

    public void reportIssue(
            int trainerId,
            EquipmentIssueRequestDTO request) {

        Trainer trainer = trainerRepo.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        Equipment equipment = equipmentRepo.findById(request.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        EquipmentIssue issue = new EquipmentIssue();

        issue.setEquipmentId(equipment);
        issue.setReportedByTrainerId(trainer);
        issue.setIssueDescription(request.getIssueDescription());
        issue.setReportedAt(LocalDateTime.now());
        issue.setStatus(EquipmentIssue.IssueStatus.OPEN);

        equipmentIssueRepo.save(issue);
    }

    public void scheduleService(
            int issueId,
            EquipmentIssueScheduleDTO request) {

        EquipmentIssue issue = equipmentIssueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Equipment issue not found"));

        if (issue.getStatus() != EquipmentIssue.IssueStatus.OPEN) {
            throw new RuntimeException("Equipment issue is not open");
        }

        issue.setServiceScheduledDate(request.getServiceScheduledDate());
        issue.setServicePersonName(request.getServicePersonName());
        issue.setServicePersonContact(request.getServicePersonContact());
        issue.setRemarks(request.getRemarks());
        issue.setStatus(EquipmentIssue.IssueStatus.SCHEDULED);

        equipmentIssueRepo.save(issue);
    }

    public void solveIssue(
            int issueId,
            EquipmentIssueSolveDTO request) {

        EquipmentIssue issue = equipmentIssueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Equipment issue not found"));

        if (issue.getStatus() != EquipmentIssue.IssueStatus.SCHEDULED) {
            throw new RuntimeException("Equipment issue is not scheduled");
        }

        issue.setResolvedDate(request.getResolvedDate());
        issue.setRemarks(request.getRemarks());
        issue.setStatus(EquipmentIssue.IssueStatus.SOLVED);

        equipmentIssueRepo.save(issue);
    }

    public List<EquipmentIssueResponseDTO> getAllIssues() {

        List<EquipmentIssue> issues = equipmentIssueRepo.findAll();

        List<EquipmentIssueResponseDTO> response = new ArrayList<>();

        for (EquipmentIssue issue : issues) {

            EquipmentIssueResponseDTO dto = new EquipmentIssueResponseDTO();

            dto.setId(issue.getId());
            dto.setEquipmentId(issue.getEquipmentId().getId());
            dto.setTrainerId(issue.getReportedByTrainerId().getId());
            dto.setIssueDescription(issue.getIssueDescription());
            dto.setReportedAt(issue.getReportedAt());
            dto.setServiceScheduledDate(issue.getServiceScheduledDate());
            dto.setServicePersonName(issue.getServicePersonName());
            dto.setServicePersonContact(issue.getServicePersonContact());
            dto.setResolvedDate(issue.getResolvedDate());
            dto.setStatus(issue.getStatus());
            dto.setRemarks(issue.getRemarks());

            response.add(dto);
        }

        return response;
    }
}
