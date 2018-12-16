package com.lookup.dao;

import com.lookup.domain.Skill;

import java.util.List;

public interface SkillsDao extends Dao<Skill> {
    void insertUserSkills(List<Skill> skills);

    List<Skill> getAllSkills();

    List<Skill> getUserSkills(int userId);
}
