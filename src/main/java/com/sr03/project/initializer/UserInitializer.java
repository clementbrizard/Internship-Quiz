package com.sr03.project.initializer;

import com.github.javafaker.Faker;
import com.sr03.project.model.User;
import com.sr03.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Random;

@Service
@Slf4j
class UserInitializer {
    private static final int NUMBER_TO_GENERATE = 100;
    private UserRepository userRepository;

    public UserInitializer(UserRepository employeeRepository) {
        this.userRepository = employeeRepository;
    }

    @PostConstruct
    public void init() {
        Random randomGenerator = new Random();
        Faker faker = new Faker();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (long i = 20; i <= NUMBER_TO_GENERATE; i++) {
            User user = User.builder()
                    .id(i)
                    .firstName(faker.gameOfThrones().character())
                    .secondName(faker.gameOfThrones().city())
                    .username(faker.name().username())
                    //.password(faker.crypto().sha1())
                    .password("$2a$11$lkx2al9pKRQoxCIxx5cuMeGDWPSPMc7lTRCrJkWl75gCaoJAK3VZC")
                    .passwordConfirm("$2a$11$lkx2al9pKRQoxCIxx5cuMeGDWPSPMc7lTRCrJkWl75gCaoJAK3VZC")
                    .mail(faker.bothify("????##@gmail.com"))
                    .company(faker.gameOfThrones().house())
                    .phone(Long.parseLong(faker.phoneNumber().phoneNumber()))
                    .creationDate(timestamp)
                    .admin(false)
                    .valid(true)
                    .build();
            userRepository.save(user);
        }
    }


}