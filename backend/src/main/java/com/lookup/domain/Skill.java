package com.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    private int skillId;
    private String name;
    private int categoryId;

    private int userId;
    private int isCoached;
    private int price;
}
