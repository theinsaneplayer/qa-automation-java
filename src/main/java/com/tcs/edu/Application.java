package com.tcs.edu;

import static com.tcs.edu.decorator.Doubling.DISTINCT;
import static com.tcs.edu.decorator.Doubling.DOUBLES;
import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.MessageOrder.DESC;
import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.services.MessageService.process;

public class Application {
    public static void main(String[] args) {
        process(MINOR, ASC, DOUBLES, "Hello world!", "Misha", "Misha", "Egor", "Vadim", "Misha", "Misha",
                "Misha", "Misha", "Misha", "Misha", "Misha", "Misha", "Misha", "Misha", "Misha", "Misha");
        /*process(MAJOR, "Hello world!", "Hello world!", "Hello world!", "Hello world!");*/
    }
}
