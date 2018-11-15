import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../domain/User";

import 'rxjs/add/operator/map'

@Injectable()
export class AccountService {

  constructor(private http: HttpClient) {}

  //login

  login(account: any): Observable<User> {

    return this.http.post<any>('login', account, {observe: 'response'})
      .map(resp => {
        let user;

        if (resp.headers.get('Bearer')) {
          user = {'login' : resp.body.login,
                  'token' : resp.headers.get('Bearer')};

          localStorage.setItem('currentUser', JSON.stringify(user));
        }

        return user;
      })
  }



  profile(login: string):Observable<any>{
    let headers = new HttpHeaders()
      .set("Authorization", `Bearer ${JSON.parse(localStorage.currentUser).token}`);

    return this.http
      .get('api/profile/' + login, {headers: headers});
  }

}
