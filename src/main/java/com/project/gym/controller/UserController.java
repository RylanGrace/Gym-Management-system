package com.project.gym.controller;

import com.project.gym.DTO.LoginResponseDTO;
import com.project.gym.DTO.RegisterDTO;
import com.project.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO register){

        return service.register(register);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody RegisterDTO register){
        return service.login(register);
    }

}
