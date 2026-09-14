package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryPaymentRequestDTO {
    private int trainerId;
    private YearMonth salaryMonth;
    private BigDecimal amount;
    private String remarks;

}
