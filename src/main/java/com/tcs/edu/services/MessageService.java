package com.tcs.edu.services;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.tcs.edu.decorator.Doubling.DISTINCT;
import static com.tcs.edu.decorator.MessageOrder.ASC;
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
    public static void process(Severity level, MessageOrder order, Doubling doubling, String... messages) {
        if (doubling != null && order != null) {
            if (doubling == DISTINCT) {
                List<String> distinctElements = Arrays.stream(messages)
                        .distinct()
                        .collect(Collectors.toList());
                for (String current : distinctElements) {
                    print(decorate(current) + " " + getSeverityValueByType(level));
                }
                String[] uniqueMessages = distinctElements.toArray(new String[0]);
                if (order == ASC) {
                    for (int i = 0; i < uniqueMessages.length; i++) {
                        print(decorate(uniqueMessages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                    }
                    return;
                }
                for (int i = uniqueMessages.length - 1; i >= 0; i--) {
                    print(decorate(uniqueMessages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                }
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
}

