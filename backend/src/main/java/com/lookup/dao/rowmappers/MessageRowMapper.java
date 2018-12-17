package com.lookup.dao.rowmappers;

import com.lookup.domain.Message;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.lookup.keys.Key.*;

@Component
public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();

        message.setMessageId(resultSet.getInt(MESSAGE_MESSAGE_ID));
        message.setSenderId(resultSet.getInt(MESSAGE_SENDER_ID));
        message.setChatId(resultSet.getInt(MESSAGE_CHAT_ID));
        message.setText(resultSet.getString(MESSAGE_TEXT));
        message.setSenderLogin(resultSet.getString(MESSAGE_SENDER_LOGIN));

        Timestamp date = resultSet.getTimestamp(MESSAGE_SENT_TIME);
        message.setSentTime(date == null ? null : date.toString());

        return message;
    }
}
