import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/api";
import {Constants} from "../domain/Constants";
import {Skill} from "../domain/Skill";
import {SkillsService} from "../service/skills.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-choose-skills',
  templateUrl: './choose-skills.component.html',
  styleUrls: ['./choose-skills.component.css']
})
export class ChooseSkillsComponent implements OnInit {

  dbSkills: Skill[] = [];
  skills: SelectItem[] = [];
  selectedSkillsToLearn: string[] = [];
  selectedSkillsToTeach: string[] = [];
  prices: number[] = [];

  constructor(private skillsService: SkillsService,
              private router: Router) {

  }

  ngOnInit() {
    this.skillsService.getAllSkills().subscribe((skills) => {
      skills.forEach((skill) => {
        this.skills.push({label: skill.name, value: skill.name});
        this.dbSkills.push(skill);
      })
    });
  }

  addSkills() {
    let userId = JSON.parse(localStorage.getItem("currentUser")).id;

    let skills: Skill[] = [];

    this.selectedSkillsToLearn.forEach((skillName) => {
      let skill: Skill = this.dbSkills.find(skill => skill.name == skillName);
      skill.isCoached = 0;
      skill.userId = userId;
      skills.push(skill);
    });

    this.selectedSkillsToTeach.forEach((skillName, index) => {
      let skill: Skill = this.dbSkills.find(skill => skill.name == skillName);
      skill.isCoached = 1;
      skill.userId = userId;
      skill.price = this.prices[index];
      skills.push(skill);
    });

    this.skillsService.insertUserSkills(skills).subscribe(
      (response) => {
        this.router.navigate(["/profile"]);
      })
  }

  addPrice(index: any, event: any){
    this.prices[index] = event.target.value;
  }

}
