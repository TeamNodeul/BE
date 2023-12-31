package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRepository extends JpaRepository< Diet,Long> {

    Optional<Diet> findAllByUser(User user);
    Optional<Diet> findByUserAndId(User user, Long Id);

}
