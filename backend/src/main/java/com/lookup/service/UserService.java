package com.lookup.service;

import com.lookup.dao.UserDao;
import com.lookup.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private Environment env;

    public User getUserById(int id) {
        log.debug("Trying to get user by id '{}' from DB", id);

        User user = userDao.findById(id);

        if (user == null) {
            log.error("User was not found by id '{}'", id);
            //TODO:throw custom exception
        }

        log.debug("Found user '{}'", user.toString());

        return user;
    }

    public User getUserByLogin(String login){
        log.debug("Trying to get user by login '{}' from DB", login);

        User user = userDao.findByLogin(login);

        if (user == null) {
            log.error("User was not found by login '{}'", login);
            //TODO:throw custom exception
        }

        log.debug("Found user '{}'", user.toString());

        return user;
    }

    public User insertUser(User user){
        log.debug("Trying to insert user with login '{}'", user.getLogin());

        User resultUser = userDao.insert(user);

        if (resultUser == null) {
            log.error("Insertion failed for user with login '{}'", user.getLogin());
            //TODO:throw custom exception
        }

        log.debug("Inserted user '{}'", resultUser.toString());

        return resultUser;
    }
}
