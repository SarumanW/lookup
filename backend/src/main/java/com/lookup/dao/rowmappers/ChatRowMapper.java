package com.lookup.dao.rowmappers;

import com.lookup.domain.Chat;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lookup.keys.Key.CHAT_CHAT_ID;
import static com.lookup.keys.Key.CHAT_COACH_ID;
import static com.lookup.keys.Key.CHAT_STUDENT_ID;

@Component
public class ChatRowMapper implements RowMapper<Chat> {
    @Override
    public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
        Chat chat = new Chat();

        chat.setChatId(resultSet.getInt(CHAT_CHAT_ID));
        chat.setCoachId(resultSet.getInt(CHAT_COACH_ID));
        chat.setStudentId(resultSet.getInt(CHAT_STUDENT_ID));

        return chat;
    }
}
