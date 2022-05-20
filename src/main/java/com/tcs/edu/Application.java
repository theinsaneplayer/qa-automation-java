package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;

import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.MessageOrder.DESC;
import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.services.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(MINOR, ASC, Doubling.DISTINCT, "Hello world!", "Misha", "Luda", "Jora", "Vadim");
        //process(MINOR, null, "Hello world!", "Hello world!", "Hello world!", "Hello world!");
    }
}
