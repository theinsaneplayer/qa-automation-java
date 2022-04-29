package com.tcs.edu.decorator;

import static java.time.Instant.*;

/**
 * Класс выводит сообщения с временной отметкой и разделителем чётных строк ---
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
    public static String decorate(String message) {
        if(messageCount++ % PAGE_SIZE == 0) {
            System.out.println("---");
        }
        return String.format("%s %s %s",messageCount, now(), message);
    }
}
