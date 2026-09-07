package com.project.gym.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private PlanName name;
    private int durationMonths;
    private BigDecimal price;
}

 enum PlanName {
     QUARTERLY,
     HALFYEARLY,
     YEARLY

 }