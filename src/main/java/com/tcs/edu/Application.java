package com.tcs.edu;

import static com.tcs.edu.decorator.Doubling.DISTINCT;
import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.services.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(MINOR, ASC, DISTINCT,"Hello world!","Hello","Hello","Hello","Hello","world","world","world");
        /*process(MAJOR, "Hello world!", "Hello world!", "Hello world!", "Hello world!");*/
    }
}
