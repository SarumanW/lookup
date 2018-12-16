package com.lookup.dao;

import com.lookup.domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    User findByLogin(String login);

    void updatePassword(User user);

    List<User> findAllCoaches(String cityName, String skillName, int startPrice, int endPrice);

    User findFullById(int id);
}