package com.project.gym.controller;

import com.project.gym.DTO.WorkoutResponseDTO;
import com.project.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private WorkoutService service;

    @GetMapping("/{memberId}/workout")
    public WorkoutResponseDTO getWorkout(@PathVariable int memberId) {
        return service.getWorkout(memberId);
    }
}
