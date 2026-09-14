package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentIssueScheduleDTO {

    private LocalDate serviceScheduledDate;
    private String servicePersonName;
    private String servicePersonContact;
    private String remarks;
}
