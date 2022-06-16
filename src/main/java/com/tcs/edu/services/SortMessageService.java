package com.tcs.edu.services;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.Printer;
import com.tcs.edu.validator.LogException;
import com.tcs.edu.validator.ValidatingService;


import static com.tcs.edu.decorator.SeverityDecorator.getSeverityValueByType;

public class SortMessageService extends ValidatingService implements MessageService {
    private final Printer printer;
    private final MessageDecorator decorator;

    public SortMessageService(Printer printer, MessageDecorator decorator) {
        this.printer = printer;
        this.decorator = decorator;
    }

    @Override
    public void log(MessageOrder messageOrder, Doubling doubling, Message... messages)
            throws LogException {
        try {
            super.isArgValid(doubling);
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
        if (doubling.equals(Doubling.DOUBLES)) {
            log(messageOrder, messages);
        } else if (doubling.equals(Doubling.DISTINCT)) {
            log(messageOrder, deduplicate(messages));
        }
    }

    @Override
    public void log(MessageOrder messageOrder, Message... messages) {
        try {
            super.isArgValid(messageOrder);
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
        if (messageOrder.equals(MessageOrder.ASC)) {
            log(messages);
        } else if (messageOrder.equals(MessageOrder.DESC)) {
            log(reverse(messages));
        }
    }

    @Override
    public void log(Message... messages) {
        try {
            super.isArgValid(messages);
        } catch (IllegalArgumentException e) {
            throw new LogException("notValidArgMessage", e);
        }
        for (Message currentMessage : messages) {
            try {
                super.isArgValid(currentMessage);
            } catch (IllegalArgumentException e) {
                throw new LogException("notValidArgMessage", e);
            }
        }
        for (Message currentMessage : messages) {
            printer.print(decorator.decorate(String.format("%s %s", currentMessage.getBody(),
                    getSeverityValueByType(currentMessage.getSeverity()))));
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
