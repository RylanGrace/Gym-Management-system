package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementRequestDTO {
    private BigDecimal heightCm;
    private BigDecimal weightKg;
    private BigDecimal waistCm;
    private BigDecimal neckCm;
}
