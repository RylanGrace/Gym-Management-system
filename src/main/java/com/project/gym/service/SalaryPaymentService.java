package com.project.gym.service;

import com.project.gym.DTO.SalaryPaymentRequestDTO;
import com.project.gym.DTO.SalaryPaymentResponseDTO;
import com.project.gym.model.SalaryPayment;
import com.project.gym.model.Trainer;
import com.project.gym.model.User;
import com.project.gym.repository.SalaryPaymentRepo;
import com.project.gym.repository.TrainerRepo;
import com.project.gym.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryPaymentService {

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SalaryPaymentRepo salaryPaymentRepo;

    public void recordSalaryPayment(
            int managerId,
            SalaryPaymentRequestDTO request) {

        Trainer trainer = trainerRepo.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        User manager = userRepo.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        SalaryPayment payment = new SalaryPayment();

        payment.setTrainerId(trainer);
        payment.setPaidByManagerId(manager);
        payment.setSalaryMonth(request.getSalaryMonth());
        payment.setAmount(request.getAmount());
        payment.setPaidAt(LocalDateTime.now());
        payment.setRemarks(request.getRemarks());

        salaryPaymentRepo.save(payment);
    }

    public List<SalaryPaymentResponseDTO> getSalaryHistory() {

        List<SalaryPayment> payments = salaryPaymentRepo.findAll();

        List<SalaryPaymentResponseDTO> response = new ArrayList<>();

        for (SalaryPayment payment : payments) {

            SalaryPaymentResponseDTO dto = new SalaryPaymentResponseDTO();

            dto.setId(payment.getId());
            dto.setTrainerId(payment.getTrainerId().getId());
            dto.setManagerId(payment.getPaidByManagerId().getId());
            dto.setSalaryMonth(payment.getSalaryMonth());
            dto.setAmount(payment.getAmount());
            dto.setPaidAt(payment.getPaidAt());
            dto.setRemarks(payment.getRemarks());

            response.add(dto);
        }

        return response;
    }
}
