import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";
import {User} from "../domain/User";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser : User;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.accountService.getFullUserById(JSON.parse(localStorage.getItem("currentUser")).id).subscribe(
      (profile) => {
        this.currentUser = profile;
        console.log(profile);
      }
    )
  }

  onTabChange(event) {
    console.log("tab pressed");
  }

}
