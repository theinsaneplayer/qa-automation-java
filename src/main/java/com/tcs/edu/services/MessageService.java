package com.tcs.edu.services;

import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.decorator.SeverityDecorator.getSeverityValueByType;
import static com.tcs.edu.decorator.TimestampMessageDecorator.*;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * Класс склеивает декорировании сообщение и уровень значимости
 * @author p.shatskov
 * @param Severity,messages Уровень значимости и массив строк сообщений
 */
public class MessageService {
    public static void process(Severity level, String... messages) {
        for (String current : messages) {
            print(decorate(current) + " " + getSeverityValueByType(level));
        }
    }
}
