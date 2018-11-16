package com.lookup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String password;

    private String email;
    private boolean isCoach;
    private int cityId;
    private String description;
}
