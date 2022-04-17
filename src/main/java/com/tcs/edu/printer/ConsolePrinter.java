package com.tcs.edu.printer;

import com.tcs.edu.decorator.Severity;

/**
 * Класс используется для вывода сообщений в консоль.
 * @author p.shatskov
 */
public class ConsolePrinter {

    /**
     * Метод print печатает сообщения для вывода в консоль.
     * @param message сообщение в виде строки
     * @author p.shatskov
     */
    public static void print(Severity severity, String message) {
        System.out.println(message);

        String severityString;
        switch (severity) {
            case MINOR: severityString = "!"; break;
            case REGULAR: severityString = "!!"; break;
            case MAJOR: severityString = "!!!"; break;
            default: severityString = "UNDEF";
        }
    }


}
