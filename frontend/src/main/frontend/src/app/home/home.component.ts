import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  inputClicked: boolean = false;
  inputText: string = "Введите желаемый навык для поиска тренера";

  constructor() { }

  ngOnInit() {
  }

}
