import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";
import {User} from "../domain/User";
import {MessageService} from "primeng/api";
import {Constants} from "../domain/Constants";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser : User;
  display: boolean = false;
  cities: any[];

  selectedCity: any;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.accountService.getFullUserById(JSON.parse(localStorage.getItem("currentUser")).id).subscribe(
      (profile) => {
        this.currentUser = profile;
        this.selectedCity = {id: profile.cityId, name: profile.cityName};
        console.log(profile);
      }
    );

    this.cities = Constants.CITIES;
  }

  onTabChange(event) {
    console.log("tab pressed");
  }

  updateUser() {
    this.currentUser.cityId = this.selectedCity.id;

    this.accountService.update(this.currentUser).subscribe((user) => {
      console.log(user);
    })
  }

}
