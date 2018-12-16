import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {CoachlistComponent} from "./coachlist/coachlist.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./service/auth.guard";
import {ProfileComponent} from "./profile/profile.component";
import {ChooseSkillsComponent} from "./choose-skills/choose-skills.component";
import {ChatComponent} from "./chat/chat.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'coaches', component: CoachlistComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'choose-skills', component: ChooseSkillsComponent, canActivate: [AuthGuard]},
  {path: 'chat/:chatId', component: ChatComponent, canActivate: [AuthGuard]}
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ]
})
export class RoutingModule {
}
