package com.lookup.controller;

import com.lookup.dao.ChatDao;
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

    @GetMapping("/chatMessages/{chatId}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable int chatId){
        log.debug("[ChatController.getChatMessages: try to get all messages for chat with id {}", chatId);

        List<Message> skills = chatDao.getChatMessages(chatId);

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/addChat")
    public ResponseEntity<Chat> insertChat(@RequestBody Chat chat){
        log.debug("[ChatController.insertChat: try to insert chat with");

        Chat result = chatDao.insert(chat);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Message> insertMessage(@RequestBody Message message){
        log.debug("[ChatController.insertMessage: try to insert message");

        Message result = chatDao.insertMessage(message);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
