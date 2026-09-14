package com.project.gym.service;

import com.project.gym.DTO.EquipmentRequestDTO;
import com.project.gym.DTO.EquipmentResponseDTO;
import com.project.gym.model.Equipment;
import com.project.gym.repository.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;


    public void addEquipment(EquipmentRequestDTO request) {

        Equipment equipment = new Equipment();

        equipment.setName(request.getName());
        equipment.setCategory(request.getCategory());
        equipment.setQuantity(request.getQuantity());
        equipment.setUnitWeightKg(request.getUnitWeightKg());
        equipment.setStatus(request.getStatus());
        equipment.setPurchaseDate(request.getPurchaseDate());
        equipment.setCreatedAt(LocalDateTime.now());

        equipmentRepo.save(equipment);
    }

    public List<EquipmentResponseDTO> getAllEquipment() {

        List<Equipment> equipmentList = equipmentRepo.findAll();

        List<EquipmentResponseDTO> response = new ArrayList<>();

        for (Equipment equipment : equipmentList) {

            EquipmentResponseDTO dto = new EquipmentResponseDTO();

            dto.setId(equipment.getId());
            dto.setName(equipment.getName());
            dto.setCategory(equipment.getCategory());
            dto.setQuantity(equipment.getQuantity());
            dto.setUnitWeightKg(equipment.getUnitWeightKg());
            dto.setStatus(equipment.getStatus());
            dto.setPurchaseDate(equipment.getPurchaseDate());
            dto.setLastServicedDate(equipment.getLastServicedDate());

            response.add(dto);
        }

        return response;
    }


}
