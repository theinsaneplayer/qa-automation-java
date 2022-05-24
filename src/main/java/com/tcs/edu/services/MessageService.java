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
     * Метод склеивает декорированные сообщения. Учитывая сортировку, уникальность, уровень важности
     *
     * @param level,order,doubling, messages
     * @author p.shatskov
     */
    public static void process(Severity level, MessageOrder order, Doubling doubling, String... messages) {
        if (messages != null) {
            List<String> filteredElements = List.of(messages);
            if (doubling == DISTINCT) {
                filteredElements = filteredElements.stream().distinct().collect(Collectors.toList());
            }
            if (order == ASC) {
                for (int i = 0; i < filteredElements.size(); i++) {
                    print(decorate(filteredElements.get(i)) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                }
                return;
            }
            for (int i = filteredElements.size() - 1; i >= 0; i--) {
                print(decorate(filteredElements.get(i)) + " " + (i + 1) + "!" + getSeverityValueByType(level));
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

