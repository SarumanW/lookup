package com.lookup.dao.impl;

import com.lookup.dao.AbstractDao;
import com.lookup.dao.SkillsDao;
import com.lookup.dao.rowmappers.SkillFullRowMapper;
import com.lookup.dao.rowmappers.SkillRowMapper;
import com.lookup.domain.Skill;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lookup.keys.Key.*;

@Repository("skillsDao")
@PropertySource("classpath:sqlDao.properties")
public class SkillsDaoImpl extends AbstractDao<Skill> implements SkillsDao {

    @Autowired
    private SkillRowMapper skillRowMapper;

    @Autowired
    private SkillFullRowMapper skillFullRowMapper;

    public SkillsDaoImpl() {
        log = LoggerFactory.getLogger(UserDaoImpl.class);
    }

    @Override
    public void insertUserSkills(List<Skill> skills) {
        log.debug("[SkillsDaoImpl.insertUserSkills]: Try to insert skills for user '{}'", skills.toString());

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_USER_SKILL);

        for (Skill skill : skills) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(USER_SKILL_IS_COACHED, skill.getIsCoached());
            parameters.put(USER_SKILL_USER_ID, skill.getUserId());
            parameters.put(USER_SKILL_SKILL_ID, skill.getSkillId());
            parameters.put(USER_SKILL_PRICE, skill.getPrice());

            try {
                log.debug("[SkillsDaoImpl.insertUserSkills]: Try to execute statement");
                simpleJdbcInsert.execute(parameters);
            } catch (DataAccessException e) {
                log.error("[SkillsDaoImpl.insertUserSkills]: Query fails by insert skills", e);
                //TODO: throw custom exception
            }
        }

        log.debug("[SkillsDaoImpl.insertUserSkills]: Skills for user with id '{}' were inserted", skills.get(0).getUserId());
    }

    @Override
    public List<Skill> getAllSkills() {
        log.debug("[SkillsDaoImpl.getAllSkills]: Try to get all skills ");

        List<Skill> skills = jdbcTemplate.query(env.getProperty(SKILL_GET_ALL_SKILLS),
                new Object[]{}, skillRowMapper);

        log.debug("[SkillsDaoImpl.getAllSkills]: Skills found: '{}'", skills);

        return skills;
    }

    @Override
    public List<Skill> getUserSkills(int userId) {
        log.debug("[SkillsDaoImpl.getUserSkills]: Try to get all skills ");

        List<Skill> skills = jdbcTemplate.query(env.getProperty(SKILL_GET_USER_SKILLS),
                new Object[]{userId}, skillFullRowMapper);

        log.debug("[SkillsDaoImpl.getUserSkills]: Skills found: '{}'", skills);

        return skills;
    }

    @Override
    public Skill findById(int id) {
        return null;
    }

    @Override
    public Skill insert(Skill model) {
        return null;
    }

    @Override
    public Skill update(Skill model) {
        return null;
    }

    @Override
    public int delete(int model) {
        return 0;
    }
}
