import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {FooterComponent} from './layout/footer/footer.component';
import {HeaderComponent} from './layout/header/header.component';
import {RoutingModule} from "./routing.module";
import {LoginComponent} from './login/login.component';
import {CoachlistComponent} from './coachlist/coachlist.component';
import {RegisterComponent} from './register/register.component';
import {DropdownModule} from 'primeng/dropdown';
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {SliderModule} from 'primeng/slider';
import {AuthGuard} from './service/auth.guard';
import {ProfileComponent} from './profile/profile.component';
import {AccountService} from "./service/account.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./service/token.interceptor";
import {AuthService} from "./service/auth.service";
import {CheckboxModule, MultiSelectModule, TabViewModule} from "primeng/primeng";
import {InputTextareaModule} from 'primeng/inputtextarea';
import {ChooseSkillsComponent} from './choose-skills/choose-skills.component';
import {SkillsService} from './service/skills.service';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { ModalWindowComponent } from './modal-window/modal-window.component';
import { ChatComponent } from './chat/chat.component';
import {ChatService} from "./service/chat.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    CoachlistComponent,
    RegisterComponent,
    ProfileComponent,
    ChooseSkillsComponent,
    ModalWindowComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    FormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    SliderModule,
    HttpClientModule,
    CheckboxModule,
    InputTextareaModule,
    MultiSelectModule,
    TabViewModule,
    MessagesModule,
    MessageModule
  ],
  providers: [
    AuthGuard,
    AccountService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService,
    SkillsService,
    ChatService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
