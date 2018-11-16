import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";
import {User} from "../domain/User";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  gotUser : User;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.accountService.getUserByLogin("admin").subscribe(
      (profile) => {
        this.gotUser = profile;
        console.log(profile);
      }

    )
  }

}
