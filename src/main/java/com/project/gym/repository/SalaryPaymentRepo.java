package com.project.gym.repository;

import com.project.gym.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryPaymentRepo extends JpaRepository<SalaryPayment,Integer> {
}
