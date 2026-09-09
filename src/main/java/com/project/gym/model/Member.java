package com.project.gym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

