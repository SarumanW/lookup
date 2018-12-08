package com.lookup.controller;

import com.lookup.dao.SkillsDao;
import com.lookup.domain.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/skills")
public class SkillsController {
    private static Logger log = LoggerFactory.getLogger(SkillsController.class);

    private final SkillsDao skillsDao;

    @Autowired
    public SkillsController(SkillsDao skillsDao) {
        this.skillsDao = skillsDao;
    }

    @PostMapping("/insertUserSkills")
    public ResponseEntity<Boolean> insertUserSkills(@RequestBody List<Skill> skills) {
        log.debug("[SkillsController.insertUserSkills: try to insert user skills");

        skillsDao.insertUserSkills(skills);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
