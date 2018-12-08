package com.lookup.keys;

public class Key {

    //TABLES
    public static final String TABLE_UUSER = "uuser";
    public static final String TABLE_USER_SKILL = "user_skill";

    //User table
    public static final String UUSER_USER_ID = "user_id";
    public static final String UUSER_LOGIN = "login";
    public static final String UUSER_PASSWORD = "password";
    public static final String UUSER_EMAIL = "email";
    public static final String UUSER_IS_COACH = "is_coach";
    public static final String UUSER_CITY_ID = "city_id";
    public static final String UUSER_DESCRIPTION = "description";

    //User-Skill table
    public static final String USER_SKILL_SKILL_ID = "skill_id";
    public static final String USER_SKILL_USER_ID = "user_id";
    public static final String USER_SKILL_IS_COACHED = "is_coached";
    public static final String USER_SKILL_PRICE = "price";

    //UserDao
    public static final String USER_FIND_BY_ID = "user.findById";
    public static final String USER_FIND_BY_LOGIN = "user.findByLogin";
    public static final String USER_FIND_COACHES = "user.findCoaches";
}
