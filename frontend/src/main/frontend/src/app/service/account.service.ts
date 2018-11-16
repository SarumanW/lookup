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

        if (resp.headers.get('Authorization')) {
          user = {'login' : resp.body.login,
                  'token' : resp.headers.get('Authorization').split(" ")[1]};

          localStorage.setItem('currentUser', JSON.stringify(user));
        }

        return user;
      })
  }

  getUserByLogin(login: string): Observable<any>{
    return this.http
      .get('api/users/' + login);
  }

  registerUser(user: User): Observable<any>{
    return this.http
      .post('api/users/sign-up', user);
  }

}
