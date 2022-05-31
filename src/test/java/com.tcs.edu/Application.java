package com.tcs.edu;

import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.services.MessageService;
import com.tcs.edu.services.SortMessageService;


import static com.tcs.edu.decorator.Doubling.DOUBLES;
import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.MessageOrder.DESC;
import static com.tcs.edu.decorator.Severity.*;


class Application {
    public static void main(String[] args) {
        MessageService messageService = new SortMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator()
        );
        Message minorMessage = new Message(MINOR, "MINOR message");
        Message regularMessage = new Message(REGULAR, "REGULAR message");
        Message majorMessage = new Message(MAJOR, "MAJOR message");
        Message message = new Message("just message");

        messageService.log(minorMessage, regularMessage, minorMessage, message);
        messageService.log(ASC, minorMessage, regularMessage, regularMessage);
        messageService.log(DESC, DOUBLES, minorMessage, regularMessage, majorMessage, message);
        messageService.log(ASC, DOUBLES, minorMessage, regularMessage, majorMessage, message);
    }
}
