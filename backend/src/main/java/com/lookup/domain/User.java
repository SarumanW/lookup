package com.lookup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String login;

    @JsonIgnore
    private String password;

    private String email;
    private int cityId;
    private String description;
}
