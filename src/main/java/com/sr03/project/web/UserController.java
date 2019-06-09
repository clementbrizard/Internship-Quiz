package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.User;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.UserRepository;
import com.sr03.project.service.SecurityService;
import com.sr03.project.service.SecurityServiceImpl;
import com.sr03.project.service.UserService;
import com.sr03.project.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

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
    private FormRepository formRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);


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

    // TODO implement a proper form handling https://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditUserForm(@PathVariable int id, Model model){
        Long lid = Long.valueOf(id);
        User user = userRepository.findById(lid);
        model.addAttribute("user", user);

        return "editUser";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user, @PathVariable int id, BindingResult bindingResult, Model model){

        // TODO implement proper validator for edited user
        /*
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "editUser";
        }*/

        Long lid = Long.valueOf(id);
        User savedUser = userRepository.findById(lid);
        user.setId(lid);
        user.setPassword(savedUser.getPassword());
        user.setPasswordConfirm(savedUser.getPasswordConfirm());
        user.setCreationDate(savedUser.getCreationDate());
        user.setValid(savedUser.getValid());

        userService.save(user);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String displayaddUserForm(Model model) {
        model.addAttribute("userForm", new User());

        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id){
        Long lid = Long.valueOf(id);
        User user = userRepository.findById(lid);
        user.setValid(!user.getValid());
        userRepository.save(user);
        return "redirect:/welcome";
    }


    @RequestMapping(value = "/isNotValid", method = RequestMethod.GET)
    public String notValid(Model model) {
        return "isNotValid";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id){
        Long lid = Long.valueOf(id);
        User user = userRepository.findById(lid);
        userRepository.delete(user);
        return "redirect:/welcome";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        String name = principal.getName();
        User user = userRepository.findByUsername(name);
        Boolean isValid = user.getValid();
        if (isValid == false){
            return "isNotValid";
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        Boolean isAdmin = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if (isAdmin == true) {
            Iterable <User> userList = userRepository.findAll();
            model.addAttribute("userList",userList);
            return "admin";
        } else {
            Iterable <Form> formList = formRepository.findAll();
            model.addAttribute("formList", formList);
            return "trainee/trainee";
        }
    }

}

