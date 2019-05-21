package com.sr03.project.service;

import com.sr03.project.model.User;

public interface UserService {
    void save(User user);

    User findByMail(String mail);
    User findByUsername(String username);
}
