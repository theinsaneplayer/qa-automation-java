package com.tcs.edu;

import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.MessageOrder.DESC;
import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.services.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(MINOR, DESC, "Hello world!", "Hello world!", "Hello world!", "Hello world!");
        //process(MINOR, null, "Hello world!", "Hello world!", "Hello world!", "Hello world!");
    }
}
