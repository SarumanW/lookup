package com.lookup.dao;

import com.lookup.domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    User findByLogin(String login);

    void updatePassword(User user);

    List<User> findAllCoaches(int cityId, int startPrice, int endPrice, int skillId);
}