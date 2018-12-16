package com.lookup.controller;

import com.lookup.dao.SkillsDao;
import com.lookup.domain.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllSkills")
    public ResponseEntity<List<Skill>> getAllSkills(){
        log.debug("[SkillsController.getAllSkills: try to get all skills");

        List<Skill> skills = skillsDao.getAllSkills();

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/getUserSkills/{userId}")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable int userId){
        log.debug("[SkillsController.getUserSkills: try to get skills for user with id {}", userId);

        List<Skill> skills = skillsDao.getUserSkills(userId);

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
