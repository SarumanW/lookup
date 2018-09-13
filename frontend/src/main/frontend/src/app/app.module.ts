import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';


import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import {RoutingModule} from "./routing.module";
import { LoginComponent } from './login/login.component';
import { CoachlistComponent } from './coachlist/coachlist.component';
import { RegisterComponent } from './register/register.component';
import {DropdownModule} from 'primeng/dropdown';
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {SliderModule} from 'primeng/slider';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    CoachlistComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    FormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    SliderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
