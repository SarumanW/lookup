import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginText: string = "";
  passwordText: string = "";

  loginPattern = "^[a-z0-9_-]{4,20}$";
  passwordPattern = "^(?=.*[a-z])(?=.*\\d){4,20}$";

  constructor(private accountService: AccountService,
              private router: Router) { }

  ngOnInit() {
  }

  loginUser(){
    this.accountService.login({'login' : this.loginText, 'password' : this.passwordText}).subscribe(
      (profile) => {
        console.log(profile);
        this.router.navigate(['/profile']);
      }
    )
  }

}
