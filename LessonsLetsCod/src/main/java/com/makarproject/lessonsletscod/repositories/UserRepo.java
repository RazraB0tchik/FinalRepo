package com.makarproject.lessonsletscod.repositories;

import com.makarproject.lessonsletscod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUsersByEmail(String email);

    User findByActivationCode(String code);

}
