package com.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private int chatId;
    private int coachId;
    private int studentId;

    private String coachLogin;

    private Message lastMessage;

    //get chat list for user
}
