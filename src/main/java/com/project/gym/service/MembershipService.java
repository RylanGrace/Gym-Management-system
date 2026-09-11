package com.project.gym.service;

import com.project.gym.DTO.TrainerMemberResponseDTO;
import com.project.gym.DTO.TrainerSetupRequestDTO;
import com.project.gym.model.*;
import com.project.gym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

    @Autowired
   private MemberRepo memberRepo;

    @Autowired
    private MembershipPlanRepo membershipPlanRepo;

    @Autowired
    private MembershipRepo membershipRepo;

    @Autowired
    private MembershipPaymentRepo membershipPaymentRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private WorkoutSplitRepo workoutSplitRepo;

    public void selectPlan(int memberId, int planId) {
       Member member = memberRepo.findById(memberId).orElseThrow();
        MembershipPlan plan = membershipPlanRepo.findById(planId).orElseThrow();

        Membership membership = new Membership();
        membership.setMember(member);
        membership.setMembershipPlan(plan);
        membership.setStatus(Membership.MembershipStatus.SCHEDULED);
        membership.setApplicationDate(LocalDate.now());
        membership.setCreatedAt(LocalDateTime.now());
       membershipRepo.save(membership);

        MembershipPayment membershipPayment = new MembershipPayment();

        membershipPayment.setMembershipId(membership);
        membershipPayment.setAmount(plan.getPrice());
        membershipPayment.setStatus(MembershipPayment.PaymentStatus.SUCCESS);
        membershipPayment.setPaidAt(LocalDateTime.now());
        membershipPayment.setPaymentReference(UUID.randomUUID().toString());

        membershipPaymentRepo.save(membershipPayment);
    }

    public List<Membership> getScheduledMemberships(){
     return membershipRepo.findByStatus(Membership.MembershipStatus.SCHEDULED);
    }

    public void managerApprove(int membershipId, int trainerId){
     Membership membership = membershipRepo.findById(membershipId).orElseThrow();

     Trainer trainer = trainerRepo.findById(trainerId).orElseThrow();

     membership.setTrainer(trainer);
     membership.setStatus(Membership.MembershipStatus.VERIFIED);
     membership.setManagerApprovedAt(LocalDateTime.now());
     membershipRepo.save(membership);

    }

    public List<TrainerMemberResponseDTO>  getVerifiedMembership(int trainerId){
     List<Membership> memberships = membershipRepo.findByTrainerIdAndStatus(trainerId, Membership.MembershipStatus.VERIFIED);
     List<TrainerMemberResponseDTO> response = new ArrayList<>();

     for (Membership membership : memberships) {

      TrainerMemberResponseDTO dto = new TrainerMemberResponseDTO();

      dto.setMembershipId(membership.getId());
      dto.setMemberId(membership.getMember().getId());
      dto.setMemberName(membership.getMember().getName());
      dto.setPhoneNo(membership.getMember().getPhoneNo());
      dto.setStatus(membership.getStatus());
      dto.setApplicationDate(membership.getApplicationDate());
      dto.setManagerApprovedAt(membership.getManagerApprovedAt());

      response.add(dto);
     }

     return response;
    }

 public void setupTrainer(int membershipId, int trainerId, TrainerSetupRequestDTO request) {

  // 1. Find membership
  Membership membership = membershipRepo.findById(membershipId)
          .orElseThrow(() -> new RuntimeException("Membership not found"));

  // 2. Check whether this trainer is assigned to the membership
  if (membership.getTrainer() == null ||
          membership.getTrainer().getId() != trainerId) {

   throw new RuntimeException("Trainer is not assigned to this membership");
  }

  // 3. Check membership status
  if (membership.getStatus() != Membership.MembershipStatus.VERIFIED) {
   throw new RuntimeException("Membership is not ready for trainer setup");
  }

  // 4. Find selected workout split
  WorkoutSplit split = workoutSplitRepo.findById(request.getWorkoutSplitId())
          .orElseThrow(() -> new RuntimeException("Workout split not found"));

  // 5. Validate training time
  LocalTime startTime = request.getStartTime();
  LocalTime endTime = request.getEndTime();

  if (!endTime.equals(startTime.plusHours(2))) {
   throw new RuntimeException("Training slot must be exactly 2 hours");
  }

  // 6. Validate that the slot is inside allowed timings
  boolean morningSlot =
          !startTime.isBefore(LocalTime.of(4, 0)) &&
                  !endTime.isAfter(LocalTime.of(10, 0));

  boolean eveningSlot =
          !startTime.isBefore(LocalTime.of(16, 0)) &&
                  !endTime.isAfter(LocalTime.of(22, 0));

  if (!morningSlot && !eveningSlot) {
   throw new RuntimeException(
           "Training slot must be between 4-10 AM or 4-10 PM"
   );
  }

  // 7. Set trainer approval time
  membership.setTrainerApprovedAt(LocalDateTime.now());

  // 8. Calculate strictly following Monday
  LocalDate joiningDate = LocalDate.now()
          .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

  membership.setJoiningDate(joiningDate);

  // 9. Calculate expiry date from joining date
  LocalDate expiryDate = joiningDate.plusMonths(
          membership.getMembershipPlan().getDurationMonths()
  );

  membership.setExpiryDate(expiryDate);

  // 10. Trainer setup is completed
  membership.setStatus(Membership.MembershipStatus.ACTIVE);

  // 11. Save membership
  membershipRepo.save(membership);
 }
}
