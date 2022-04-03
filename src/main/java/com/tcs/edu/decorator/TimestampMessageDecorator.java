package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Класс выводит сообщения с временной отметкой
 * Метод берёт текущаю дату и время, соединяя их с заданным сообщением
 * @param message принимает строки
 * @author p.shatskov
 */
public class TimestampMessageDecorator {
    public static void decorate(String message) {
        String decoratedMessage = Instant.now() + " " + message;
        System.out.println(decoratedMessage);
    }
}
