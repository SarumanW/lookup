package com.lookup.dao.rowmappers;

import com.lookup.domain.Skill;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lookup.keys.Key.*;

@Component
public class SkillFullRowMapper implements RowMapper<Skill> {
    @Override
    public Skill mapRow(ResultSet resultSet, int i) throws SQLException {
        Skill skill = new Skill();

        skill.setSkillId(resultSet.getInt(SKILL_SKILL_ID));
        skill.setCategoryId(resultSet.getInt(SKILL_CATEGORY_ID));
        skill.setName(resultSet.getString(SKILL_NAME));
        skill.setCategoryName(resultSet.getString(SKILL_CATEGORY_NAME));
        skill.setIsCoached(resultSet.getInt(SKILL_IS_COACHED));

        return skill;
    }
}
