package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import static java.time.Instant.*;

/**
 * Класс выводит сообщения с временной отметкой и разделителем чётных строк ---
 * @author p.shatskov
 */
public class TimestampMessageDecorator implements MessageDecorator {
    public static int messageCount = 0;
    public static final int PAGE_SIZE = 2;

    /**
     * Метод берёт текущаю дату и время, соединяя их с заданным сообщением.
     * Добавлен счётчик на каждый вывод.
     * @param message принимает строку
     * @author p.shatskov
     */
    public Message decorate(Message message) {
        var decoratedMessage = String.format("%s %s %s",messageCount, now(), message.getBody());
        if(messageCount++ % PAGE_SIZE == 0) {
            System.out.println("---");
        }
        message.setBody(decoratedMessage);
        return message;
    }
}
