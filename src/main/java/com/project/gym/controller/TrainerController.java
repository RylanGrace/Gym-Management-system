package com.project.gym.controller;

import com.project.gym.DTO.TrainerMemberResponseDTO;
import com.project.gym.DTO.TrainerSetupRequestDTO;
import com.project.gym.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private MembershipService service;

    @GetMapping("/{trainerId}/verified")
    public List<TrainerMemberResponseDTO> getVerifiedMembership(@PathVariable int trainerId){
        return service.getVerifiedMembership(trainerId);
    }

    @PostMapping("/{trainerId}/memberships/{membershipId}/setup")
    public void setupTrainer(
            @PathVariable int trainerId,
            @PathVariable int membershipId,
            @RequestBody TrainerSetupRequestDTO request) {

        service.setupTrainer(membershipId, trainerId, request);
    }
}
