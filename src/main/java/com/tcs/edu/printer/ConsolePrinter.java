package com.tcs.edu.printer;

/**
 * Класс используется для вывода сообщений в консоль.
 * @author p.shatskov
 */
public class ConsolePrinter implements Printer {

    /**
     * Метод print печатает сообщения для вывода в консоль.
     * @param message сообщение в виде строки
     * @author p.shatskov
     */
    public void print(String message) {
        System.out.println(message);
    }
}
