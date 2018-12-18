import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";
import {User} from "../domain/User";
import {MessageService} from "primeng/api";
import {Constants} from "../domain/Constants";
import {SkillsService} from "../service/skills.service";
import {Skill} from "../domain/Skill";
import {ChatService} from "../service/chat.service";
import {Chat} from "../domain/Chat";
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser : User;
  display: boolean = false;
  cities: any[];
  chats: Chat[] = [];

  skillsToLearn: Skill[] = [];
  skillsToTeach: Skill[] = [];

  selectedCity: any;

  constructor(private accountService: AccountService,
              private skillsService: SkillsService,
              private chatService: ChatService,
              private router: Router) { }

  ngOnInit() {
    let userId = JSON.parse(localStorage.getItem("currentUser")).id;

    this.accountService.getFullUserById(userId).subscribe(
      (profile) => {
        this.currentUser = profile;
        this.selectedCity = {id: profile.cityId, name: profile.cityName};
        console.log(profile);
      }
    );

    this.skillsService.getUserSkills(userId).subscribe(
      (skills) => {
        skills.forEach((skill) => {
          if(skill.isCoached === 1){
            this.skillsToTeach.push(skill);
          } else {
            this.skillsToLearn. push(skill);
          }
        })
      }
    );

    this.cities = Constants.CITIES;
  }

  onTabChange(event) {
    if(event.index == 1){
      this.chatService.getChats(this.currentUser.id).subscribe((chats) => {
          this.chats = chats;
      })
    }
  }

  updateUser() {
    this.currentUser.cityId = this.selectedCity.id;

    this.accountService.update(this.currentUser).subscribe((user) => {
      console.log(user);
    })
  }

  openChat(chatId: number){
    this.router.navigate(['/chat/' + chatId]);
  }

}
