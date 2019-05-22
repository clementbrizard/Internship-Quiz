package com.sr03.project.repository;

import com.sr03.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByMail(String mail);
    List<User> getAllUsers();
    User getUserById(Long id);
}
