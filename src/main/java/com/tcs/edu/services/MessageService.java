package com.tcs.edu.services;

import com.tcs.edu.decorator.Severity;

import java.util.Objects;

import static com.tcs.edu.decorator.SeverityDecorator.getSeverityValueByType;
import static com.tcs.edu.decorator.TimestampMessageDecorator.*;
import static com.tcs.edu.printer.ConsolePrinter.print;


/**
 * Класс обрабатывает сообщения согласно бизнес-логике
 *
 * @author p.shatskov
 */
public class MessageService {
    /**
     * Метод склеивает декорировании сообщение и уровень значимости
     *
     * @param Severity,messages Уровень значимости и массив строк сообщений
     * @author p.shatskov
     */
    public static void process(Severity level, String message, String... messages) {
        if (Objects.equals(message, messages)) {
            for (String current : messages) {
                print(decorate(current) + " " + getSeverityValueByType(level));
            }
        }
    }
}
