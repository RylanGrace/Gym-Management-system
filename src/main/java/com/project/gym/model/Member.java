package com.project.gym.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNo;
    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;
}

enum Gender{
    MALE,
    FEMALE;
}