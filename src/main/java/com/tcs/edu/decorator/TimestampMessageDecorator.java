package com.tcs.edu.decorator;

import static java.time.Instant.*;

/**
 * Класс выводит сообщения с временной отметкой
 * @author p.shatskov
 */
public class TimestampMessageDecorator {
    public static int messageCount = 0;
    public static final int PAGE_SIZE = 2;

    /**
     * Метод берёт текущаю дату и время, соединяя их с заданным сообщением.
     * Добавлен счётчик на каждый вывод.
     * @param message принимает строку
     * @author p.shatskov
     */
    public static void decorate(String message) {
        if(messageCount++ % PAGE_SIZE == 0) {
            System.out.println("---");
        }
        System.out.printf("%s %s %s%n", messageCount, now(), message);
    }
}
