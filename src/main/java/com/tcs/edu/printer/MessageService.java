package com.tcs.edu.printer;

import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static com.tcs.edu.decorator.TimestampMessageDecorator.*;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 *
 *
 */
public class MessageService {
    public static void process(Severity level, String... messages) {
        for (String current : messages) {
            print(decorate(current) + " " + mapToString(level));
        }
    }
}
