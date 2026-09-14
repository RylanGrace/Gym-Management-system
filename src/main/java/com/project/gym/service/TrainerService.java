package com.project.gym.service;


import com.project.gym.DTO.TrainerResponseDTO;
import com.project.gym.model.Trainer;
import com.project.gym.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {


    @Autowired
    private TrainerRepo trainerRepo;

    public List<TrainerResponseDTO> getAllTrainers() {

        List<Trainer> trainers = trainerRepo.findAll();

        List<TrainerResponseDTO> response = new ArrayList<>();

        for (Trainer trainer : trainers) {

            TrainerResponseDTO dto = new TrainerResponseDTO();

            dto.setId(trainer.getId());
            dto.setName(trainer.getName());
            dto.setGender(trainer.getGender());

            response.add(dto);
        }

        return response;
    }
}


