package com.lookup.controller;

import com.lookup.dao.UserDao;
import com.lookup.dao.impl.UserDaoImpl;
import com.lookup.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        log.debug("[UserController.getUserByName]: Trying to get user by name '{}'", name);

        User user = userDao.findByLogin(name);

        log.debug("[UserController.getUserByName]: Send response body user '{}' and status OK", user.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<User> getFullUserById(@PathVariable int id) {
        log.debug("[UserController.getFullUserById]: Trying to get full user by id '{}'", id);

        User user = userDao.findFullById(id);

        log.debug("[UserController.getFullUserById]: Send response body user '{}' and status OK", user.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        log.debug(user.toString());

        user.setPassword(bCryptPasswordEncoder.encode
                (user.getPassword()));

        userDao.insert(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<User>> getAllCoaches(@RequestParam("cityName") String cityName,
                                                    @RequestParam("skillName") String skillName,
                                                    @RequestParam("startPrice") String startPrice,
                                                    @RequestParam("endPrice") String endPrice) {
        log.debug("[UserController.getAllCoaches]: Trying to get all coaches");

        List<User> coaches = userDao.findAllCoaches(cityName, skillName, Integer.valueOf(startPrice),
                Integer.valueOf(endPrice));

        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }
}

