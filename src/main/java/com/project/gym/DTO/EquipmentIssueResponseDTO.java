package com.project.gym.DTO;

import com.project.gym.model.EquipmentIssue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentIssueResponseDTO {
    private int id;
    private int equipmentId;
    private int trainerId;
    private String issueDescription;
    private LocalDateTime reportedAt;
    private LocalDate serviceScheduledDate;
    private String servicePersonName;
    private String servicePersonContact;
    private LocalDate resolvedDate;
    private EquipmentIssue.IssueStatus status;
    private String remarks;

}
