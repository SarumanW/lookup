package com.lookup.dao.impl;

import com.lookup.dao.AbstractDao;
import com.lookup.dao.ChatDao;
import com.lookup.dao.rowmappers.MessageRowMapper;
import com.lookup.domain.Chat;
import com.lookup.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lookup.keys.Key.*;

@Repository("chatDao")
@PropertySource("classpath:sqlDao.properties")
public class ChatDaoImpl extends AbstractDao<Chat> implements ChatDao {

    @Autowired
    private MessageRowMapper messageRowMapper;

    @Override
    public List<Message> getChatMessages(int chatId) {
        log.debug("[ChatDaoImpl.getChatMessages]: Try to get messages for chat with id {}", chatId);

        List<Message> messages = jdbcTemplate.query(env.getProperty(CHAT_GET_CHAT_MESSAGES),
                new Object[]{chatId}, messageRowMapper);

        log.debug("[ChatDaoImpl.getChatMessages]: Messages found: '{}'", messages);

        return messages;
    }

    @Override
    public Message insertMessage(Message message) {
        log.debug("[ChatDaoImpl.insertMessage]: Try to insert message in chat with id '{}'", message.getChatId());

        int id;

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_MESSAGE)
                .usingGeneratedKeyColumns(MESSAGE_MESSAGE_ID);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put(MESSAGE_CHAT_ID, message.getChatId());
        parameters.put(MESSAGE_SENDER_ID, message.getSenderId());
        parameters.put(MESSAGE_SENDER_LOGIN, message.getSenderLogin());
        parameters.put(MESSAGE_TEXT, message.getText());
        parameters.put(MESSAGE_SENT_TIME, message.getSentTime() != null ? Timestamp.valueOf(message.getSentTime()) : null);

        try {
            log.debug("[ChatDaoImpl.insertMessage]: Try to execute statement");
            id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
            message.setMessageId(id);
        } catch (DataAccessException e) {
            log.error("[ChatDaoImpl.insertMessage]: Query fails by insert message", e);
            //TODO: throw custom exception
        }

        log.debug("[ChatDaoImpl.insertMessage]: Message with id '{}' was inserted", message.getMessageId());

        return message;
    }

    @Override
    public List<Chat> getChatsByStudentId(int studentId) {
        return null;
    }

    @Override
    public Chat findById(int id) {
        return null;
    }

    @Override
    public Chat insert(Chat model) {
        log.debug("[ChatDaoImpl.insert]: Try to insert chat with id '{}'", model.getChatId());

        int id;

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_CHAT)
                .usingGeneratedKeyColumns(CHAT_CHAT_ID);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CHAT_COACH_ID, model.getCoachId());
        parameters.put(CHAT_STUDENT_ID, model.getStudentId());

        try {
            log.debug("[ChatDaoImpl.insert]: Try to execute statement");
            id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
            model.setChatId(id);
        } catch (DataAccessException e) {
            log.error("[ChatDaoImpl.insert]: Query fails by insert chat", e);
            //TODO: throw custom exception
        }

        log.debug("[ChatDaoImpl.insert]: Chat with id '{}' was inserted", model.getChatId());

        return model;
    }

    @Override
    public Chat update(Chat model) {
        return null;
    }

    @Override
    public Chat delete(Chat model) {
        return null;
    }
}
