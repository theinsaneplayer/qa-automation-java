package com.tcs.edu;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.services.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(MINOR, "Hello world!");
        process(MAJOR, "Hello world!", "Hello world!");
        process(REGULAR, "Hello world!", "Hello world!", "Hello world!");
    }
}
