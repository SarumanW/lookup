package com.lookup.controller;

import com.lookup.dao.ChatDao;
import com.lookup.domain.AnalyticVM;
import com.lookup.domain.Chat;
import com.lookup.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/chat")
public class ChatController {
    private static Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ChatDao chatDao;

    @Autowired
    public ChatController(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Chat>> getChatsForUserId(@PathVariable int userId){
        log.debug("[ChatController.getChatsForUserId: try to get all chats for user with id '{}'", userId);

        List<Chat> chats = chatDao.getChatsByStudentId(userId);

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping("/chatMessages/{chatId}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable int chatId){
        log.debug("[ChatController.getChatMessages: try to get all messages for chat with id '{}'", chatId);

        List<Message> skills = chatDao.getChatMessages(chatId);

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/addChat")
    public ResponseEntity<Chat> insertChat(@RequestBody Chat chat){
        log.debug("[ChatController.insertChat: try to insert chat");

        Chat result = chatDao.insert(chat);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Message> insertMessage(@RequestBody Message message){
        log.debug("[ChatController.insertMessage: try to insert message");

        Message result = chatDao.insertMessage(message);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/analytic/{word}")
    public ResponseEntity<List<AnalyticVM>> getWordAnalytic(@PathVariable String word){
        log.debug("[ChatController.getWordAnalytic: try to get analytic for word '{}'", word);

        List<AnalyticVM> analyticVM = chatDao.getWordAnalytic(word);

        return new ResponseEntity<>(analyticVM, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<Boolean> deleteChat(@PathVariable int chatId) {
        log.debug("[ChatController.deleteChat: try to delete chat with id '{}'", chatId);

        chatDao.delete(chatId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
