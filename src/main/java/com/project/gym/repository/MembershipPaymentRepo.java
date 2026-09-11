package com.project.gym.repository;

import com.project.gym.model.MembershipPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipPaymentRepo extends JpaRepository<MembershipPayment,Integer> {
}
