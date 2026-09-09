package com.project.gym.repository;

import com.project.gym.model.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPlanRepo extends JpaRepository<MembershipPlan, Integer> {



}
