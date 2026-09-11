package com.project.gym.controller;

import com.project.gym.DTO.AssignTrainerDTO;
import com.project.gym.model.Membership;
import com.project.gym.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private MembershipService service;

    @GetMapping("/memberships/scheduled")
    public List<Membership> getScheduled(){
        return service.getScheduledMemberships();
    }

    @PostMapping("/memberships/{membershipId}/verify")
    public void verifyMembership(@PathVariable int membershipId, @RequestBody AssignTrainerDTO request){
        service.managerApprove(membershipId,request.getTrainerId());
    }

}
