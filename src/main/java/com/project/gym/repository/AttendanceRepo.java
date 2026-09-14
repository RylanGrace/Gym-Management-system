package com.project.gym.repository;

import com.project.gym.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByMemberId_Id(int memberId);
}
