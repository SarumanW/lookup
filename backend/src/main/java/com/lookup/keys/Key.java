package com.lookup.keys;

public class Key {

    //TABLES
    public static final String TABLE_UUSER = "uuser";
    public static final String TABLE_USER_SKILL = "user_skill";
    public static final String TABLE_SKILL = "skill";

    //User table
    public static final String UUSER_USER_ID = "user_id";
    public static final String UUSER_LOGIN = "login";
    public static final String UUSER_PASSWORD = "password";
    public static final String UUSER_EMAIL = "email";
    public static final String UUSER_CITY_ID = "city_id";
    public static final String UUSER_DESCRIPTION = "description";
    public static final String UUSER_CITY_NAME = "name";
    public static final String UUSER_LAST_COMMENT_TEXT_ABOUT = "text";
    public static final String UUSER_LAST_COMMENT_RECIEVER_NAME = "reciever_name";
    public static final String UUSER_PRICE= "price";

    //User-Skill table
    public static final String USER_SKILL_SKILL_ID = "skill_id";
    public static final String USER_SKILL_USER_ID = "user_id";
    public static final String USER_SKILL_IS_COACHED = "is_coached";
    public static final String USER_SKILL_PRICE = "price";

    //Skill table
    public static final String SKILL_SKILL_ID = "skill_id";
    public static final String SKILL_NAME = "name";
    public static final String SKILL_CATEGORY_ID = "category_id";
    public static final String SKILL_CATEGORY_NAME = "category_name";
    public static final String SKILL_IS_COACHED = "is_coached";

    //UserDao
    public static final String USER_FIND_BY_ID = "user.findById";
    public static final String USER_FIND_BY_LOGIN = "user.findByLogin";
    public static final String USER_FIND_COACHES = "user.findCoaches";
    public static final String USER_FIND_FULL_BY_ID = "user.findFullById";
    public static final String USER_UPDATE="user.update";

    //SkillDao
    public static final String SKILL_GET_ALL_SKILLS = "skill.getAllSkills";
    public static final String SKILL_GET_USER_SKILLS = "skill.getUserSkills";
}
