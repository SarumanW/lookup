package com.lookup.controller;

import com.lookup.domain.Uuser;
import com.lookup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{name}")
    public ResponseEntity<Uuser> getUserByName(@PathVariable String name) {
        log.debug("Trying to get uuser by name '{}'", name);

        Uuser uuser = userRepository.findByName(name);

        log.debug("Send response body uuser '{}' and status OK", uuser.toString());

        return new ResponseEntity<>(uuser, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Uuser>> getAllUsers() {
        log.debug("Trying to get all uusers");

        List<Uuser> uusers = userRepository.findAll();

        log.debug("Found all uusers: '{}'", uusers.toString());

        return new ResponseEntity<>(uusers, HttpStatus.OK);
    }
}