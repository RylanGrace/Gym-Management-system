package com.project.gym.repository;

import com.project.gym.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
}
