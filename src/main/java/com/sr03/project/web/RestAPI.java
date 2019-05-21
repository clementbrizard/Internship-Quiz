package com.sr03.project.web;

import com.sr03.project.model.User;
import com.sr03.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;



@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
public class RestAPI {
    @Autowired
    private UserRepository userRepository;

    private Map<Long, User> custUsers = new HashMap<Long, User>();

    @PostConstruct
    public void initIt() throws Exception {
        for (int i = 1 ; i < userRepository.findAll().size() ; i++) {
            custUsers.put(Long.valueOf(i),userRepository.findAll().get(i));
        }
    }

    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    public List<User> getResource() {

        List<User> UsersList = custUsers.entrySet().stream()
                    .map(entry -> entry.getValue())
                    .collect(Collectors.toList());

        return UsersList;
    }
}
