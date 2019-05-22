package com.sr03.project.service;

import com.sr03.project.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByMail(String mail);
    User findByUsername(String username);
    public List<User> getAllUsers();
    public User getUserById(Long id);
}
