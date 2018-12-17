package com.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int messageId;
    private int senderId;
    private String text;
    private int chatId;
    private String sentTime;
    private String senderLogin;
}
