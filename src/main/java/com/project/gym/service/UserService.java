package com.project.gym.service;

import com.project.gym.model.RegisterDTO;
import com.project.gym.model.User;
import com.project.gym.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    User user = new User();

    public String register(RegisterDTO register){
        LocalDateTime currentTime = LocalDateTime.now();
      user.setEmail(register.getEmail());
      user.setPasswordHash(register.getPassword());
      user.setRole(User.UserRole.MEMBER);
      user.setCreatedAt(currentTime);
      user.setEnabled(true);
      try {
          User ifSaved = userRepo.save(user);
          return "Registered successfully" + ifSaved.getId();
      }
      catch (DataAccessException e) {
          return "Register failed";
      }
    }


}
