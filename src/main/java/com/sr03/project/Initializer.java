package com.sr03.project;

import com.github.javafaker.Faker;
import com.sr03.project.model.User;
import com.sr03.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@Component
public class Initializer {
    private static final int NUMBER_TO_GENERATE = 100;
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Initializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        List<User> userList = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (long i = 0; i <= NUMBER_TO_GENERATE; i++) {
            String password = bCryptPasswordEncoder.encode(faker.gameOfThrones().quote());
            User user = User.builder()
                    .id(faker.random().nextLong())
                    .firstName(faker.gameOfThrones().character())
                    .secondName(faker.gameOfThrones().city())
                    .username(faker.name().username())
                    //.password(faker.crypto().sha1())
                    .password(password)
                    .passwordConfirm(password)
                    .mail(faker.bothify("????##@gmail.com"))
                    .company(faker.gameOfThrones().house())
                    .phone(faker.phoneNumber().phoneNumber())
                    .creationDate(timestamp)
                    .admin(false)
                    .valid(true)
                    .build();
            this.userRepository.save(user);
            userList.add(user);

        }
       // userList.forEach(System.out::println);
    }


}