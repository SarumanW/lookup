package com.lookup.controller;

import com.lookup.domain.User;
import com.lookup.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable int id) {
//        log.debug("Trying to get user by id '{}'", id);
//
//        User user = userService.getUserById(id);
//
//        log.debug("Send response body user '{}' and status OK", user.toString());
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        log.debug("Trying to get user by name '{}'", name);

        User user = userService.getUserByLogin(name);

        log.debug("Send response body user '{}' and status OK", user.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

