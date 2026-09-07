package com.project.gym.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Trainer trainerId;
    @ManyToOne
    private User paidByManagerId;
    private YearMonth salaryMonth;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private String remarks;
}
