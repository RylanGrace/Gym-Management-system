package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryPaymentResponseDTO {
    private int id;
    private int trainerId;
    private int managerId;
    private YearMonth salaryMonth;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private String remarks;

}
