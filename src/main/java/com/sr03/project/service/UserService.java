package com.sr03.project.service;

import com.sr03.project.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
