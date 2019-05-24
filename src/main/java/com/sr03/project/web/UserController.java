package com.sr03.project.web;

import com.github.javafaker.Faker;
import com.sr03.project.model.User;
import com.sr03.project.repository.UserRepository;
import com.sr03.project.service.SecurityService;
import com.sr03.project.service.UserService;
import com.sr03.project.validator.UserValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

/*
    @RequestMapping(value = {"/init"},method = RequestMethod.GET)
    public String init(Model model){
        int NUMBER_TO_GENERATE = 100;
        Faker faker = new Faker();
        List<User> userList = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (long i = 0; i <= NUMBER_TO_GENERATE; i++) {
            User user = new User();
            user.setFirstName(faker.gameOfThrones().character());
            user.setSecondName(faker.gameOfThrones().city());
            user.setUsername(faker.name().username());
            //.password(faker.crypto().sha1())
            String password = bCryptPasswordEncoder.encode(faker.gameOfThrones().quote());
            user.setPassword(password);
            user.setPasswordConfirm(password);
            user.setMail(faker.bothify("????##@gmail.com"));
            user.setCompany(faker.gameOfThrones().house());
            user.setPhone(faker.phoneNumber().phoneNumber());
            user.setCreationDate(timestamp);
            user.setAdmin(false);
            user.setValid(true);
            userRepository.save(user);
            userList.add(user);

        }
        // userList.forEach(System.out::println);
        return "welcome";
    }
*/

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        String currentUsername = securityService.findLoggedInUsername();
        User currentUser = userRepository.findByUsername(currentUsername);
        Iterable <User> userList = userRepository.findAll();
        model.addAttribute("currentUsername",currentUsername);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("userList",userList);
        System.out.println("AAAAA DEBUG AZAZAZ");
        System.out.println(currentUsername);
        return "welcome";
    }

}
