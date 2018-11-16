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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lookup.keys.Key.*;

@Repository("userDao")
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
                    new Object[]{login}, userRowMapper);

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
    public void updatePassword(User user) {

    }

    @Override
    public List<User> findAllCoaches(int cityId, int startPrice, int endPrice, int skillId) {
        log.debug("Try to get coaches by params '{}' '{}' '{}' '{}'", cityId, startPrice, endPrice, skillId);

        List<User> coaches = jdbcTemplate.query(env.getProperty(USER_FIND_COACHES),
                new Object[]{cityId, startPrice, endPrice, skillId}, userRowMapper);

        log.debug("Coaches found: '{}'", coaches);

        return coaches;
    }

    @Override
    public User findById(int id) {
        log.debug("Try to find User by id: '{}'", id);

        User user = null;

        try {
            user = jdbcTemplate.queryForObject(
                    env.getProperty(USER_FIND_BY_ID),
                    new Object[]{id}, userRowMapper);

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
        log.debug("Try to insert user with login '{}'", model.getLogin());

        int id;

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_UUSER)
                .usingGeneratedKeyColumns(UUSER_USER_ID);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put(UUSER_USER_ID, model.getId());
        parameters.put(UUSER_LOGIN, model.getLogin());
        parameters.put(UUSER_PASSWORD, model.getPassword());
        parameters.put(UUSER_EMAIL, model.getEmail());
        parameters.put(UUSER_IS_COACH, model.isCoach() ? 1 : 0);
        parameters.put(UUSER_CITY_ID, model.getCityId());
        parameters.put(UUSER_DESCRIPTION, model.getDescription());

        try {
            log.debug("Try to execute statement");
            id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
            model.setId(id);
        } catch (DataAccessException e) {
            log.error("Query fails by insert User",e);
            //TODO: throw custom exception
        }

        log.debug("User with login '{}' was inserted", model.getLogin());

        return model;
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