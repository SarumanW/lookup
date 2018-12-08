package com.lookup.dao.rowmappers;

import com.lookup.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lookup.keys.Key.*;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt(UUSER_USER_ID));
        user.setLogin(resultSet.getString(UUSER_LOGIN));
        user.setPassword(resultSet.getString(UUSER_PASSWORD));
        user.setEmail(resultSet.getString(UUSER_EMAIL));
        user.setDescription(resultSet.getString(UUSER_DESCRIPTION));
        return user;
    }
}