import {Component, OnInit} from '@angular/core';
import {User} from "../domain/User";
import {SelectItem} from "primeng/api";
import {Dropdown} from "primeng/primeng";
import {Skill} from "../domain/Skill";
import {SkillsService} from "../service/skills.service";
import {Router} from "@angular/router";
import {AccountService} from "../service/account.service";
import {Category} from "../domain/Category";
import {Constants} from "../domain/Constants";

@Component({
  selector: 'app-coachlist',
  templateUrl: './coachlist.component.html',
  styleUrls: ['./coachlist.component.css']
})
export class CoachlistComponent implements OnInit {
  coaches: User[] = [];
  categories: Category[] = [];
  skills: Skill[] = [];
  shownSkills: Skill[] = [];
  rangeValues: number[] = [200, 500];

  cities: SelectItem[] = [];

  selectedCategory: Category;
  selectedSkill: Skill;
  selectedCity: any;

  panelAvailable: boolean = false;

  constructor(private accountService: AccountService,
              private skillsService: SkillsService,
              private router: Router) {

  }

  ngOnInit() {
    this.skillsService.getAllSkills().subscribe((skills) => {
      let basicCategory = new Category();
      basicCategory.name = 'Все';
      this.categories[0] = basicCategory;

      skills.forEach((skill) => {
        this.skills.push(skill);
        if (!this.categories.some(c => c.categoryId === skill.categoryId)) {
          this.categories.push({categoryId: skill.categoryId, name: skill.categoryName});
        }
      });

      this.shownSkills = this.skills;
      this.panelAvailable = true;
    });

    this.cities = Constants.CITIES;
  }

  selectLesson() {
    this.shownSkills = this.selectedCategory.name === 'Все' ? this.skills :
      this.skills.filter(skill => skill.categoryName === this.selectedCategory.name);
  }

  clearFilter(dropdown: Dropdown) {
    dropdown.resetFilter();
  }

  findCoaches(){
    this.accountService.getAllCoaches(this.selectedCity.name, this.selectedSkill.name,
                                      this.rangeValues[0], this.rangeValues[1])
      .subscribe((coaches) => {
      this.coaches = coaches;
    })
  }

}
