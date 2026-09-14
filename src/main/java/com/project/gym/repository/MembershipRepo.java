package com.project.gym.repository;

import com.project.gym.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MembershipRepo extends JpaRepository<Membership, Integer> {

   Membership findByMember_Id(int memberId);

   List<Membership> findByStatus(Membership.MembershipStatus status);

   List<Membership> findByTrainerIdAndStatus(int trainerId,Membership.MembershipStatus status);

   List<Membership> findByStatusAndExpiryDateBefore(
           Membership.MembershipStatus status,
           LocalDate date);

   List<Membership> findAllByMember_Id(int memberId);


}
