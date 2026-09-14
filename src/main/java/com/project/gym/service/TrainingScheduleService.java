package com.project.gym.service;

import com.project.gym.DTO.TrainingScheduleResponseDTO;
import com.project.gym.model.TrainingSchedule;
import com.project.gym.repository.TrainingScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingScheduleService {

    @Autowired
    private TrainingScheduleRepo trainingScheduleRepo;

    public List<TrainingScheduleResponseDTO> getMemberSchedule(int memberId) {

        List<TrainingSchedule> schedules =
                trainingScheduleRepo.findByMemberId_Id(memberId);

        if (schedules == null) {
            throw new RuntimeException("Training schedule not found");
        }

        List<TrainingScheduleResponseDTO> response = new ArrayList<>();

        for (TrainingSchedule schedule : schedules ) {
            TrainingScheduleResponseDTO dto =
                    new TrainingScheduleResponseDTO();

            dto.setScheduleId(schedule.getId());
            dto.setMemberId(schedule.getMemberId().getId());
            dto.setTrainerId(schedule.getTrainerId().getId());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setEffectiveForm(schedule.getEffectiveForm());
            dto.setEffectiveTo(schedule.getEffectiveTo());
            dto.setStatus(schedule.getStatus());

            response.add(dto);
        }
        return response;
    }

    public List<TrainingScheduleResponseDTO> getTrainerSchedules(int trainerId) {

        List<TrainingSchedule> schedules =
                trainingScheduleRepo.findByTrainerId_Id(trainerId);

        if (schedules == null) {
            throw new RuntimeException("Training schedule not found");
        }

        List<TrainingScheduleResponseDTO> response = new ArrayList<>();

        for (TrainingSchedule schedule : schedules) {

            TrainingScheduleResponseDTO dto =
                    new TrainingScheduleResponseDTO();

            dto.setScheduleId(schedule.getId());
            dto.setMemberId(schedule.getMemberId().getId());
            dto.setTrainerId(schedule.getTrainerId().getId());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setEffectiveForm(schedule.getEffectiveForm());
            dto.setEffectiveTo(schedule.getEffectiveTo());
            dto.setStatus(schedule.getStatus());

            response.add(dto);
        }

        return response;
    }

}
