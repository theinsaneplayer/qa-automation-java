package com.tcs.edu;

import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.printer.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(Severity.MINOR, "Hello world!");
        process(Severity.MAJOR, "Hello world!", "Hello world!");
        process(Severity.REGULAR, "Hello world!", "Hello world!", "Hello world!");
    }
}
