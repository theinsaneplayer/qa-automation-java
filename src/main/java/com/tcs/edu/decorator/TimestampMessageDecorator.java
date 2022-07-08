package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import static java.time.Instant.*;

/**
 * Класс выводит сообщения с временной отметкой и разделителем чётных строк ---
 * @author p.shatskov
 */
public class TimestampMessageDecorator implements MessageDecorator {
    public static int messageCount = 1;
    public static final int PAGE_SIZE = 2;

    /**
     * Метод берёт текущаю дату и время, соединяя их с заданным сообщением.
     * На каждый вывод установлен счётчик.
     * @param Message - объект хранящий конструкторы
     * @author p.shatskov
     */
    @Override
    public Message decorate(Message message) {
        var decoratedMessage = String.format("%s %s %s",messageCount, now(), message);
        System.out.printf("%s %s%n", decoratedMessage, "\n");
        if(messageCount++ % PAGE_SIZE == 0) {
            System.out.println("----------------");
        }
        message.setBody(decoratedMessage);
        return message;
    }
}
