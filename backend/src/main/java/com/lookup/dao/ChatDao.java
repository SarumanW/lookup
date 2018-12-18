package com.lookup.dao;

import com.lookup.domain.AnalyticVM;
import com.lookup.domain.Chat;
import com.lookup.domain.Message;

import java.util.List;

public interface ChatDao extends Dao<Chat> {
    List<Message> getChatMessages(int chatId);

    Message insertMessage(Message message);

    List<Chat> getChatsByStudentId(int studentId);

    boolean insertUserChat(int chatId, int studentId, int coachId);

    List<AnalyticVM> getWordAnalytic(String word);
}
