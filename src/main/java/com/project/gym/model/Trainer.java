package com.project.gym.model;

import jakarta.persistence.*;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
