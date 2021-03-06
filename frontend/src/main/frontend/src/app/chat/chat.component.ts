import {Component, HostListener, OnDestroy, OnInit} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../domain/User";
import {Message} from "../domain/Message";
import {ChatService} from "../service/chat.service";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  serverUrl = 'http://localhost:8099/socket';
  stompClient;
  messageText: string;
  user: User;
  chatId: number;
  currentUserLogin: string;
  ws: any;
  colors: any;
  color: string;
  preventMessages: Message[] = [];

  isTyping: boolean = false;
  typingMemberText: string = '';
  typingMembers: string[] = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private chatService: ChatService) {

  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.currentUser);

    this.route.params.subscribe(params => {
      this.chatId = params['chatId'];
    });

    this.currentUserLogin = this.user.login;

    this.colors = [
      '#2196F3', '#32c787', '#00BCD4', '#ff5652',
      '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
    ];

    // this.initializeWebSocketConnection();

    this.chatService.getMessages(this.chatId).subscribe(
      (messages) => {
        this.preventMessages = messages;

        for (let message of messages) {
          let messageElement;
          this.color = this.colors[ChatComponent.hashCode(message.senderId.toString()) % 8];
          let time = this.getTimeFromDate(message.messageDate);

          messageElement = `<li class="chat-message" style="padding-left: 68px;
                              position: relative;">
                                <i style="position: absolute;
                              width: 42px;
                              height: 42px;
                              overflow: hidden;
                              left: 10px;
                              display: inline-block;
                              vertical-align: middle;
                              font-size: 18px;
                              line-height: 42px;
                              color: #fff;
                              text-align: center;
                              border-radius: 50%;
                              font-style: normal;
                              text-transform: uppercase;
                              background-color:${this.color}";>${message.senderLogin[0]}</i>
                                <span style="color: #333;
                              font-weight: 600;">${message.senderLogin}</span>
                              <span style="color: #333;
                              font-weight: 600;">${time}</span>
                                <p style="color: #43464b;">${message.text}</p>
                            </li>`;

          $('#messageArea').append(messageElement);
        }

        $('#messageArea').scrollTop($('#messageArea').prop('scrollHeight'));

        this.initializeWebSocketConnection();
      });
  }

  initializeWebSocketConnection() {

    let userName = this.user.login;

    this.ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(this.ws);
    let that = this;

    this.stompClient.connect({}, function () {
      that.stompClient.subscribe("/chat/" + that.chatId, (payload) => {

        let message = JSON.parse(payload.body);
        let messageElement;
        let time = that.getTimeFromDate(message.messageDate);

        if (message.type === 'JOIN') {

          message.content = message.sender + ' joined!';
          messageElement = `<li class="event-message" style="width: 100%;
                              text-align: center;
                              clear: both;">
                              <p style="color: #777;
                              font-size: 14px;
                              word-wrap: break-word;">${message.content}</p>
                            </li>`;
        } else if (message.type === 'LEAVE') {
          message.content = message.sender + ' left!';
          messageElement = `<li class="event-message" style="width: 100%;
                              text-align: center;
                              clear: both;">
                               <p style="color: #777;
                              font-size: 14px;
                              word-wrap: break-word;">${message.content}</p>
                            </li>`;

          that.stopTypingMember(message.sender);
        } else if (message.type === 'TYPING') {
          const memberIndex = that.typingMembers.indexOf(message.sender);

          if (memberIndex === -1) {
            that.typingMembers.push(message.sender);
          }

          that.typingsMembersNotification();

        } else if (message.type === 'NOT_TYPING') {
          that.stopTypingMember(message.sender);
        } else {
          that.color = that.colors[ChatComponent.hashCode(message.sender) % 8];

          messageElement = `<li class="chat-message" style="padding-left: 68px;
                              position: relative;">
                              <i style="position: absolute;
                              width: 42px;
                              height: 42px;
                              overflow: hidden;
                              left: 10px;
                              display: inline-block;
                              vertical-align: middle;
                              font-size: 18px;
                              line-height: 42px;
                              color: #fff;
                              text-align: center;
                              border-radius: 50%;
                              font-style: normal;
                              text-transform: uppercase;
                              background-color:${that.color}";>${message.sender[0]}</i>
                              <span style="color: #333;
                              font-weight: 600;">${message.sender}</span>
                              <span style="color: #333;
                              font-weight: 600;">${time}</span>
                              <p style="color: #43464b;">${message.content}</p>
                            </li>`;
          that.stopTypingMember(message.sender);
        }

        $('#messageArea').append(messageElement);
        $('#messageArea').scrollTop($('#messageArea').prop('scrollHeight'));
      })
    });

    setTimeout(() => {
      this.stompClient.send("/app-chat/add/" + this.chatId, {}, JSON.stringify({sender: userName, type: 'JOIN'}));
    }, 5000)
  }

  sendMessage() {
    let chatMessage = {
      sender: this.user.login,
      content: this.messageText,
      type: 'CHAT'
    };

    let mess: Message = new Message();
    mess.chatId = this.chatId;
    mess.text = this.messageText;
    mess.senderId = this.user.id;
    mess.sentTime = this.getCurrentDate();

    this.chatService.addMessage(mess).subscribe(
      (message) => {
        this.stompClient.send("/app-chat/send/message/" + this.chatId, {}, JSON.stringify(chatMessage));
      }
    );

    this.messageText = '';
    this.isUserTypingMessage();
  }

  // USER TYPE TEXT EVENTS

  isUserTypingMessage() {
    if (this.messageText === '' && this.isTyping) {
      this.isTyping = false;
      this.sendIsTypingMember('NOT_TYPING');
    } else if (!this.isTyping) {
      this.isTyping = true;
      this.sendIsTypingMember('TYPING');
    }
  }

  sendIsTypingMember(messageType: string) {
    let chatMessage = {
      sender: this.user.login,
      type: messageType
    };

    this.stompClient.send("/app-chat/send/message/" + this.chatId, {}, JSON.stringify(chatMessage));
  }

  stopTypingMember(sender: string) {
    const memberIndex = this.typingMembers.indexOf(sender);

    if (memberIndex !== -1) {
      this.typingMembers.splice(memberIndex, 1);
    }

    this.typingsMembersNotification();
  }

  typingsMembersNotification() {
    if (this.typingMembers.length === 0) {
      this.typingMemberText = '';
    } else {
      this.typingMemberText = '';
      let that = this;

      this.typingMembers.forEach(function (member) {
        that.typingMemberText += member + ', ';
      });

      this.typingMemberText = this.typingMemberText.replace(new RegExp(', ' + '$'), ' ');

      if (this.typingMembers.length === 1 && this.typingMembers[1] !== this.user.login) {
        this.typingMemberText += 'is typing ...';
      } else {
        this.typingMemberText += 'are typing ...'
      }
    }
  }

  static hashCode(name: string) {
    let hash = 0;
    if (name.length == 0) return hash;
    for (let i = 0; i < name.length; i++) {
      let char = name.charCodeAt(i);
      hash = ((hash << 5) - hash) + char;
      hash = hash & hash;
    }
    return Math.abs(hash);
  }

  ngOnDestroy() {
    if (this.stompClient !== undefined) {
      let chatMessage = {
        sender: this.user.login,
        type: 'LEAVE'
      };
      this.stompClient.send("/app-chat/send/message/" + this.chatId, {}, JSON.stringify(chatMessage));
      this.ws.close();
    }
  }

  getCurrentDate(): string {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();
    let time = ChatComponent.formatDate(hour) + ":" + ChatComponent.formatDate(min) + ":" + ChatComponent.formatDate(sec);
    return year + "-" + ChatComponent.formatDate(month) + "-" + ChatComponent.formatDate(day) + " " + time;
  }

  static formatDate(dateUnit: number): string {
    return (dateUnit < 10 ? "0" + dateUnit : dateUnit.toString());
  }

  getTimeFromDate(date: string): string {
    if (!date) {
      date = this.getCurrentDate();
    }
    let time = date.split(" ")[1];
    time = time.substr(0, 8);
    return time;
  }
}
