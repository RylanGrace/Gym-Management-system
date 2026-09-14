package com.project.gym.repository;

import com.project.gym.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepo extends JpaRepository<Measurement,Integer> {
    List<Measurement> findByMemberId_Id(int memberId);
}
