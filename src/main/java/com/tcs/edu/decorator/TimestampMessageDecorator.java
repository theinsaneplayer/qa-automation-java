package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс выводит сообщения с временной отметкой
 * @author p.shatskov
 */
public class TimestampMessageDecorator {
    public static int messageCount = 1;

    /**
     * Метод берёт текущаю дату и время, соединяя их с заданным сообщением.
     * Добавлен счётчик на каждый вывод.
     * @param message
     * @author p.shatskov
     */
    public static void decorate(String message) {
        final var decoratedMessage = Instant.now() + " " + message;
        System.out.println((messageCount++) + " " + decoratedMessage);
    }
}
