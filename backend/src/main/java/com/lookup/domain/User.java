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
    private boolean isCoach;
    private int cityId;
}
