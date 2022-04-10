package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс выводит сообщения с временной отметкой
 * @author p.shatskov
 */
public class TimestampMessageDecorator {
    public static int messageCount = 1;
    public static final int PAGE_SIZE = 2;

    /**
     * Метод берёт текущаю дату и время, соединяя их с заданным сообщением.
     * Добавлен счётчик на каждый вывод.
     * @param message
     * @author p.shatskov
     */
    public static void decorate(String message) {

        final var decorate = Instant.now() + " " + message;
        final var counter = messageCount++;
        if(messageCount % PAGE_SIZE == 0) {
            System.out.println("---");
        }
        System.out.println(counter + " " + decorate);
    }
}
