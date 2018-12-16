import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Skill} from "../domain/Skill";

@Injectable()
export class SkillsService {
  constructor(private http: HttpClient) {
  }

  insertUserSkills(skills: Skill[]): Observable<any> {
    return this.http
      .post('api/skills/insertUserSkills', skills);
  }

  getAllSkills(): Observable<any> {
    return this.http.get('api/skills/getAllSkills');
  }
}
