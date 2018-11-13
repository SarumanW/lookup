package com.lookup.dao;

import com.lookup.domain.User;

public interface UserDao extends Dao<User> {

    User findByLogin(String login);

    User findByEmail(String email);

    User update(User model);

    void updatePassword(User user);

    boolean isLoginFree(String login);

    boolean isEmailFree(String email);
}