import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/api";
import {Constants} from "../domain/Constants";
import {Skill} from "../domain/Skill";
import {SkillsService} from "../service/skills.service";

@Component({
  selector: 'app-choose-skills',
  templateUrl: './choose-skills.component.html',
  styleUrls: ['./choose-skills.component.css']
})
export class ChooseSkillsComponent implements OnInit {

  skills: SelectItem[] = [];
  selectedSkillsToLearn: string[] = [];
  selectedSkillsToTeach: string[] = [];

  constructor(private skillsService: SkillsService) {

  }

  ngOnInit() {
    Constants.SKILLS.forEach((skill) => {
      this.skills.push({label: skill.name, value: skill.name});
    })
  }

  addSkills() {
    let userId = JSON.parse(localStorage.getItem("currentUser")).id;

    let skills: Skill[] = [];

    this.selectedSkillsToLearn.forEach((skillName) => {
      let skill: Skill = Constants.SKILLS.find(skill => skill.name == skillName);
      skill.isCoached = 0;
      skill.userId = userId;
      skills.push(skill);
    });

    this.selectedSkillsToTeach.forEach((skillName) => {
      let skill: Skill = Constants.SKILLS.find(skill => skill.name == skillName);
      skill.isCoached = 1;
      skill.userId = userId;
      skills.push(skill);
    });

    this.skillsService.insertUserSkills(skills).subscribe(
      (response) => {

      })
  }

}
