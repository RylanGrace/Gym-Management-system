package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementResponseDTO {

    private int measurementId;
    private int memberId;
    private int trainerId;
    private LocalDate measurementDate;
    private BigDecimal heightCm;
    private BigDecimal weightKg;
    private BigDecimal waistCm;
    private BigDecimal neckCm;
}
