package com.tcs.edu.services;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.domain.Message;

/**
 * Интерфейс обработки сообщений
 */
public interface MessageService {

    void log(Doubling doubling, Message... messages);
    void log(Message... messages);
    void log(MessageOrder messageOrder, Message... messages);
    void log(MessageOrder messageOrder, Doubling doubling, Message... messages);
}
