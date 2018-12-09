import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
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
                  'id' : resp.body.id,
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

  getFullUserById(id: number): Observable<any>{
    return this.http
      .get('api/users/full/' + id);
  }

  registerUser(user: User): Observable<any>{
    return this.http
      .post('api/users/sign-up', user);
  }

  getAllCoaches(cityId : number, startPrice : number, endPrice : number, skillId : number) :  Observable<any>{
    let params = new HttpParams().set("cityId", cityId.toString()).set("startPrice", startPrice.toString())
      .set("endPrice", endPrice.toString()).set("skillId", skillId.toString());

    return this.http
      .get('api/users/get-coaches', {params : params});
  }

}
