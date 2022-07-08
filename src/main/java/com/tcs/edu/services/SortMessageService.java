package com.tcs.edu.services;

import com.tcs.edu.decorator.*;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.MessageRepository;
import com.tcs.edu.validator.LogException;
import com.tcs.edu.validator.ValidatingService;

import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.decorator.SeverityDecorator.getSeverityValueByType;

public class SortMessageService extends ValidatingService implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDecorator decorator;
    private final SeverityDecorator levelMapper = new SeverityDecorator();

    public SortMessageService(MessageRepository messageRepository, MessageDecorator decorator) {
        this.messageRepository = messageRepository;
        this.decorator = decorator;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity severity) {
        return messageRepository.findBySeverity(severity);
    }

    @Override
    public void log(MessageOrder messageOrder, Doubling doubling, Message... messages)
            throws LogException {
        try {
            super.isArgValid(doubling);
            if (doubling.equals(Doubling.DOUBLES)) {
                log(messageOrder, messages);
            } else if (doubling.equals(Doubling.DISTINCT)) {
                log(messageOrder, deduplicate(messages));
            }
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
    }

    @Override
    public void log(MessageOrder messageOrder, Message... messages) {
        try {
            super.isArgValid(messageOrder);
            if (messageOrder.equals(MessageOrder.ASC)) {
                log(messages);
            } else if (messageOrder.equals(MessageOrder.DESC)) {
                log(reverse(messages));
            }
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
    }

    @Override
    public void log(Message... messages) {
        try {
            super.isArgValid(messages);
            for (Message currentMessage : messages) {
                super.isArgValid(currentMessage);
                messageRepository.create(currentMessage);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new LogException("notValidArgMessage", e);
        }
    }

    @Override
    public void log(Doubling doubling, Message... messages) {
        log(MessageOrder.ASC, doubling, messages);
    }

    /**
     * @param messages массив сообщений
     * @return массив сообщений без дубликатов
     */
    private Message[] deduplicate(Message... messages) {
        Message[] messagesOutput = new Message[messages.length];
        if (messages.length != 0) {
            messagesOutput[0] = messages[0];
            int j = 1;
            for (int i = 1; i < messages.length; i++) {
                if (!checkContains(messages[i], messagesOutput)) {
                    messagesOutput[j] = messages[i];
                    j++;
                }
            }
        }
        return messagesOutput;
    }

    /**
     * @param messages массив сообщений String[]
     * @return массив сообщений в обратном порядке
     */
    private Message[] reverse(Message... messages) {
        int j = messages.length;
        Message[] messagesReverse = new Message[j];
        for (int i = 0; i < j; i++) {
            messagesReverse[i] = messages[j - i - 1];
        }
        return messagesReverse;
    }

    /**
     * @param message  сообщение String
     * @param messages массив сообщений String[]
     */
    private boolean checkContains(Message message, Message[] messages) {
        if (message != null) {
            for (Message s : messages) {
                if (s != null && s.getBody().equals(message.getBody())) {
                    return true;
                }
            }
        }
        return false;
    }
}
