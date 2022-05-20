package com.tcs.edu.services;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;

import java.util.Arrays;
import java.util.List;
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
    public static void process(Severity level, MessageOrder order, String... messages) {
        if (order != null) {
            int Order = messages.length;
            if (order == MessageOrder.ASC) {
                for (int i = 0; i < Order; i++) {
                    print(decorate(messages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                }
                return;
            }
            for (int i = messages.length - 1; i >= 0; i--) {
                print(decorate(messages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
            }
        }
    }

    public static void process(Severity level, String... messages) {
        if (messages != null) {
            for (String current : messages) {
                print(decorate(current) + " " + getSeverityValueByType(level));
            }
        }
    }

    public static void process(Severity level, MessageOrder order, Doubling doubling, String... messages) {
        if (doubling != null && order != null) {
            if (doubling == Doubling.DISTINCT) {
                String[] arrayStr = new String[messages.length];
                Arrays.stream(arrayStr).distinct().forEach(System.out::println);
                for (String current : messages) {
                    print(decorate(current) + " " + getSeverityValueByType(level));
                }
            }
        }
    }

}

