package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentCategory category;
    private int quantity;
    private BigDecimal unitWeightKg;
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;
    private LocalDate purchaseDate;
    private LocalDate lastServicedDate;
    private LocalDateTime createdAt;

 public enum EquipmentCategory {
        CARDIO,
        STRENGTH,
        FREE_WEIGHT,
        FLEXIBILITY;
    }

    public enum EquipmentStatus {
        ACTIVE,
        DAMAGED;
    }

}


