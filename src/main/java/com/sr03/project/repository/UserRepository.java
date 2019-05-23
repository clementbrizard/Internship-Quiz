package com.sr03.project.repository;

import com.sr03.project.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends DataTablesRepository<User, Integer> {
    User findByUsername(String username);
    User findByMail(String mail);
    /*List<User> getAllUsers();*/
}
