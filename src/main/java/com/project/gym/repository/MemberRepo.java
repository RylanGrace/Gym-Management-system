package com.project.gym.repository;

import com.project.gym.model.Member;
import com.project.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer> {

    Member findByUser(User user);
}
