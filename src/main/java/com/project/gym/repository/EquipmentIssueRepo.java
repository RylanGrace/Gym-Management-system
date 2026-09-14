package com.project.gym.repository;

import com.project.gym.model.EquipmentIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentIssueRepo extends JpaRepository<EquipmentIssue,Integer> {
}
