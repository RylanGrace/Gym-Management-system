package com.project.gym.repository;

import com.project.gym.model.Trainer;
import com.project.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

    Trainer findByUser(User user);

}
