package com.lookup.dao.impl;

import com.lookup.dao.AbstractDao;
import com.lookup.dao.UserDao;
import com.lookup.dao.rowmappers.UserRowMapper;
import com.lookup.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import static com.lookup.keys.Key.USER_FIND_BY_ID;
import static com.lookup.keys.Key.USER_FIND_BY_LOGIN;

@Repository
@PropertySource("classpath:sqlDao.properties")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    private UserRowMapper userRowMapper;

    public UserDaoImpl() {
        log = LoggerFactory.getLogger(UserDaoImpl.class);
    }

    @Override
    public User findByLogin(String login) {
        log.debug("Try to find User by login: '{}'", login);

        User user = null;

        try {
            user = jdbcTemplate.queryForObject(
                    env.getProperty(USER_FIND_BY_LOGIN),
                    new Object[]{login}, new UserRowMapper() {
                    }
            );

        } catch (EmptyResultDataAccessException e) {
            log.error("User with login '{}' not found", login, e);
            //TODO: throw custom exception
        }catch (DataAccessException e) {
            log.error("Query fails by finding user with login '{}'", login, e);
            //TODO: throw custom exception
        }

        log.debug("User with login '{}' was found", login);

        return user;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void updatePassword(User user) {

    }

    @Override
    public boolean isLoginFree(String login) {
        return false;
    }

    @Override
    public boolean isEmailFree(String email) {
        return false;
    }

    @Override
    public User findById(int id) {
        log.debug("Try to find User by id: '{}'", id);

        User user = null;

        try {
            user = jdbcTemplate.queryForObject(
                    env.getProperty(USER_FIND_BY_ID),
                    new Object[]{id}, new UserRowMapper() {
                    }
            );

        } catch (EmptyResultDataAccessException e) {
            log.error("User with id '{}' not found", id,e);
            //TODO: throw custom exception
        }catch (DataAccessException e) {
            log.error("Query fails by finding user with id '{}'", id,e);
            //TODO: throw custom exception
        }

        log.debug("User with id '{}' was found", id);

        return user;
    }

    @Override
    public User insert(User model) {
        return null;
    }

    @Override
    public User update(User model) {
        return null;
    }

    @Override
    public User delete(User model) {
        return null;
    }
}