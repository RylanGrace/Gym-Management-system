package com.project.gym.service;

import com.project.gym.DTO.AttendanceResponseDTO;
import com.project.gym.model.Attendance;
import com.project.gym.model.Member;
import com.project.gym.repository.AttendanceRepo;
import com.project.gym.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private MemberRepo memberRepo;

    public void recordAttendance(int memberId) {

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Attendance attendance = new Attendance();

        attendance.setMemberId(member);
        attendance.setCheckInDateTime(LocalDateTime.now());

        attendanceRepo.save(attendance);
    }

    public List<AttendanceResponseDTO> getMemberAttendance(int memberId) {

        List<Attendance> attendanceList =
                attendanceRepo.findByMemberId_Id(memberId);

        List<AttendanceResponseDTO> response = new ArrayList<>();

        for (Attendance attendance : attendanceList) {

            AttendanceResponseDTO dto = new AttendanceResponseDTO();

            dto.setAttendanceId(attendance.getId());
            dto.setMemberId(attendance.getMemberId().getId());
            dto.setCheckInDateTime(attendance.getCheckInDateTime());

            response.add(dto);
        }

        return response;
    }
}
