package com.project.gym.DTO;

import com.project.gym.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDTO {

    private String name;
    private Equipment.EquipmentCategory category;
    private int quantity;
    private BigDecimal unitWeightKg;
    private Equipment.EquipmentStatus status;
    private LocalDate purchaseDate;
}
