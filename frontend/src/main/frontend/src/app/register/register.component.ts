import { Component, OnInit } from '@angular/core';
import {User} from "../domain/User";
import {SelectItem} from "primeng/api";
import {AccountService} from "../service/account.service";
import {Router} from "@angular/router";
import {Constants} from "../domain/Constants";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  selectedCity: any;
  isCoach: boolean;

  loginPattern = "^[a-z0-9_-]{4,20}$";
  passwordPattern = "^[a-z0-9_-]{4,20}$";
  emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

  cities: SelectItem[] = [];

  constructor(private accountService: AccountService,
              private router: Router) {
  }

  ngOnInit() {
    this.user.cityId = 1;
    this.cities = Constants.CITIES;
  }

  register(){
    this.accountService.registerUser(this.user).subscribe(
      (user) => {
        console.log(user);
        this.accountService.login(this.user).subscribe((resp) =>{
          console.log(resp);
          this.router.navigate(['/choose-skills']);
        });
      }
    )
  }

  changeCity(){
    console.log("change value of city id");
    this.user.cityId = this.selectedCity.id;
  }
}
