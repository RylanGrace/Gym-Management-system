package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean enabled;
    private LocalDateTime createdAt;

    public enum UserRole {
        MEMBER,
        TRAINER,
        MANAGER
    }

}
