package com.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private int isCoach;
    private int cityId;
}
