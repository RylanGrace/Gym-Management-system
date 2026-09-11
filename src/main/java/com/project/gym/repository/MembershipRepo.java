package com.project.gym.repository;

import com.project.gym.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepo extends JpaRepository<Membership, Integer> {

   List<Membership> findByStatus(Membership.MembershipStatus status);

   List<Membership> findByTrainerIdAndStatus(int trainerId,Membership.MembershipStatus status);
}
