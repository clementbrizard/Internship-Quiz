package com.sr03.project.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.sr03.project.model.User;
import com.sr03.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class RestUserController {
        @Autowired
        private UserRepository userRepository;

        @JsonView(DataTablesOutput.View.class)
        @RequestMapping(value = "/users", method = RequestMethod.GET)
        public DataTablesOutput<User> getUsers(@Valid @RequestBody DataTablesInput input) {
            return userRepository.findAll(input);
        }

    }


