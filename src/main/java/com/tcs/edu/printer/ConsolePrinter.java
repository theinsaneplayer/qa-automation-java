package com.tcs.edu.printer;

import com.tcs.edu.validator.ValidatingService;

/**
 * Класс используется для вывода сообщений в консоль.
 * @author p.shatskov
 */
public class ConsolePrinter extends ValidatingService implements Printer {

    /**
     * Метод print печатает сообщения для вывода в консоль.
     * @param message сообщение в виде строки
     * @author p.shatskov
     */
    @Override
    public void print(String message) {
        if (isArgNotValid(message)) { return; }
        System.out.println(message);
    }
}
