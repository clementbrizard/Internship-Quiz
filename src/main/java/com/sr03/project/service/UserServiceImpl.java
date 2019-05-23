package com.sr03.project.service;

import com.sr03.project.model.User;
import com.sr03.project.repository.RoleRepository;
import com.sr03.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.setValid(true);
        user.setAdmin(user.getAdmin());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreationDate(timestamp);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    /*    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
}
