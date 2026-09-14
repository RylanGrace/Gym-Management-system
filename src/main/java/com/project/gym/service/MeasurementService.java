package com.project.gym.service;

import com.project.gym.DTO.MeasurementRequestDTO;
import com.project.gym.DTO.MeasurementResponseDTO;
import com.project.gym.model.Measurement;
import com.project.gym.model.Membership;
import com.project.gym.repository.MeasurementRepo;
import com.project.gym.repository.MembershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepo measurementRepo;

    @Autowired
    private MembershipRepo membershipRepo;


    public void recordMeasurement(int memberId, int trainerId, MeasurementRequestDTO request){

        Membership membership = membershipRepo.findByMember_Id(memberId);

        if (membership == null) {
            throw new RuntimeException("Membership not found");
        }

        if (membership.getTrainer() == null ||
                membership.getTrainer().getId() != trainerId) {
            throw new RuntimeException(
                    "Trainer is not assigned to this member");
        }

        if (LocalDate.now().getDayOfWeek() != DayOfWeek.MONDAY) {
            throw new RuntimeException("Measurements can only be recorded on Monday");
        }
        Measurement measurement = new Measurement();

        measurement.setMemberId(membership.getMember());
        measurement.setRecordedByTrainerId(membership.getTrainer());
        measurement.setMeasurementDate(LocalDate.now());

        measurement.setHeightCm(request.getHeightCm());
        measurement.setWeightKg(request.getWeightKg());
        measurement.setWaistCm(request.getWaistCm());
        measurement.setNeckCm(request.getNeckCm());

        measurementRepo.save(measurement);

    }

    public List<MeasurementResponseDTO> getMemberMeasurements(int memberId) {

        List<Measurement> measurements =
                measurementRepo.findByMemberId_Id(memberId);

        List<MeasurementResponseDTO> response = new ArrayList<>();

        for (Measurement measurement : measurements) {

            MeasurementResponseDTO dto = new MeasurementResponseDTO();

            dto.setMeasurementId(measurement.getId());
            dto.setMemberId(measurement.getMemberId().getId());
            dto.setTrainerId(measurement.getRecordedByTrainerId().getId());
            dto.setMeasurementDate(measurement.getMeasurementDate());
            dto.setHeightCm(measurement.getHeightCm());
            dto.setWeightKg(measurement.getWeightKg());
            dto.setWaistCm(measurement.getWaistCm());
            dto.setNeckCm(measurement.getNeckCm());

            response.add(dto);
        }

        return response;
    }
}
