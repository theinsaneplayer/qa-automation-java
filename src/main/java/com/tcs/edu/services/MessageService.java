package com.tcs.edu.services;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;

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
    public static void
    process(Severity level, MessageOrder order, String... messages) {
        if (messages != null) {
            int reverseOrder = messages.length;
            if (order == MessageOrder.ASC) {
                for (int i = 0; i < reverseOrder; i++) {
                    print(decorate(messages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                }
            } else {
                for (int i = messages.length - 1; i >= 0; i--) {
                    print(decorate(messages[i]) + " " + (i + 1) + "!" + getSeverityValueByType(level));
                }
            }
        }
    }

}
