package com.project.gym.controller;

import com.project.gym.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    @Autowired
    private MembershipService service;

  @PostMapping("/select-plan")
  public void selectplan(int memberId, int planId){
    service.selectPlan(memberId, planId);
  }


}
