package com.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private int cityId;
    private String description;

    private String cityName;
    private List<Skill> userSkills;
    private List<Chat> userChats;
    private String lastCommentText;
}
