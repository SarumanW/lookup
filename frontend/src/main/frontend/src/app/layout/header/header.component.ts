import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  isUserLoggedIn() : boolean{
    return localStorage.getItem("currentUser") === null;
  }

  logout() {
    localStorage.clear();
  }

}
