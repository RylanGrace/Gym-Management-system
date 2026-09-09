package com.project.gym.service;

import com.project.gym.DTO.*;
import com.project.gym.model.*;
import com.project.gym.repository.MemberRepo;
import com.project.gym.repository.MembershipPlanRepo;
import com.project.gym.repository.TrainerRepo;
import com.project.gym.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MembershipPlanRepo membershipPlanRepo;

    @Autowired
    private TrainerRepo trainerRepo;




    public String register(RegisterDTO register) {
        User user = new User();
        LocalDateTime currentTime = LocalDateTime.now();
        user.setEmail(register.getEmail());
        user.setPasswordHash(register.getPassword());
        user.setRole(User.UserRole.MEMBER);
        user.setCreatedAt(currentTime);
        user.setEnabled(true);
        Member member = new Member();

        member.setUser(user);
        member.setName(register.getName());
        member.setGender(register.getGender());
        member.setPhoneNo(register.getPhoneNo());


        try {
            User ifSaved = userRepo.save(user);
            Member name =memberRepo.save(member);
            return "Hello " + name.getName() +" Welcome You Registered successfully ";
        } catch (DataAccessException e) {
            return "Register failed";
        }
    }


    public LoginResponseDTO login(RegisterDTO register) {

        User user = userRepo.findByEmail(register.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();

        if (user == null) {
            response.setMessage("Invalid email or password");
            return response;
        }

        if (!Objects.equals(user.getPasswordHash(), register.getPassword())) {
            response.setMessage("Invalid email or password");
            return response;
        }
        response.setMessage("Login successful");
        response.setRole(String.valueOf(user.getRole()));
        if (user.getRole() == User.UserRole.MEMBER) {

            Member member = memberRepo.findByUser(user);
            Membership latestMembership = member.getMemberships()
                    .stream()
                    .max(Comparator.comparing(Membership::getCreatedAt))
                    .orElse(null);

            if (latestMembership != null &&
                    latestMembership.getStatus() == Membership.MembershipStatus.ACTIVE) {
                MemberResponseDTO memberResponse = new MemberResponseDTO();

                memberResponse.setId(member.getId());
                memberResponse.setName(member.getName());
                memberResponse.setGender(member.getGender());
                memberResponse.setPhoneNo(member.getPhoneNo());

                response.setMemberResponseDTO(memberResponse);


            } else {

                List<MembershipPlanDTO> plans = membershipPlanRepo.findAll()
                        .stream()
                        .map(plan -> {
                            MembershipPlanDTO dto = new MembershipPlanDTO();

                            dto.setId(plan.getId());
                            dto.setDuration(plan.getDurationMonths());
                            dto.setName(String.valueOf(plan.getName()));
                            dto.setPrice(plan.getPrice());

                            return dto;
                        })
                        .toList();

                response.setPlans(plans);
            }

        }
        if (user.getRole() == User.UserRole.TRAINER) {
            Trainer trainer = trainerRepo.findByUser(user);

            TrainerResponseDTO trainerResponse = new TrainerResponseDTO();

            trainerResponse.setId(trainer.getId());
            trainerResponse.setName(trainer.getName());
            trainerResponse.setGender(trainer.getGender());

            response.setTrainerResponseDTO(trainerResponse);

        } else if (user.getRole() == User.UserRole.MANAGER) {

            UserResponseDTO userResponse = new UserResponseDTO();

            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole());

            response.setUserResponseDTO(userResponse);
        }

        return response;

    }
}
