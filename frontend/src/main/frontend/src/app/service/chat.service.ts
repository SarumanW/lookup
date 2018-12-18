import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ChatService {

  constructor(private http: HttpClient) {
  }

  getMessages(chatId: number): Observable<any> {
    return this.http.get('api/chat/chatMessages/' + chatId);
  }

  addMessage(message: any): Observable<any> {
    return this.http.post('api/chat/addMessage', message)
  }

  addChat(chat: any): Observable<any> {
    return this.http.post('api/chat/addChat', chat);
  }

  getChats(userId: number): Observable<any> {
    return this.http.get('api/chat/' + userId);
  }

  getAnalytic(word: string): Observable<any> {
    return this.http.get('api/chat/analytic/' + word);
  }
}
