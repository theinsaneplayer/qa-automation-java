package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.services.MessageService;
import com.tcs.edu.services.SortMessageService;
import com.tcs.edu.validator.LogException;


import static com.tcs.edu.decorator.Doubling.DISTINCT;
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
        Message messageWithoutSeverity = new Message("сообщение без уровня важности");
        Message messageEmpty = new Message("");
        Message messageError = new Message("данное сообщение не должно печататься");


        /*messageService.log((Doubling) null, messageError);
        messageService.log(null, (Doubling) null, messageError);
        messageService.log(DISTINCT, null, null, null, null);
        messageService.log(ASC, DOUBLES);
        messageService.log();
        messageService.log(null, DOUBLES, messageError);
        messageService.log(ASC, (Doubling) null, messageError);
        messageService.log((Doubling) null, messageError);
        messageService.log(null, (Doubling) null, messageError);

        messageService.log(ASC, DOUBLES, minorMessage, regularMessage, majorMessage, messageWithoutSeverity);
        messageService.log(DESC, DISTINCT, null, regularMessage, null, messageWithoutSeverity,
                null, messageWithoutSeverity, messageWithoutSeverity, null);
        messageService.log(DOUBLES, minorMessage, regularMessage, regularMessage);
        messageService.log(ASC, minorMessage, regularMessage, regularMessage);
        messageService.log(minorMessage, regularMessage, minorMessage, messageWithoutSeverity);
        messageService.log(DISTINCT, null, null, null, null);

        Message minorWarning1 = new Message(MINOR, "WARNING 1");
        Message minorWarning2 = new Message(MINOR, "WARNING 2");
        Message majorWarning = new Message(MAJOR, "WARNING");
        Message majorError = new Message(MAJOR, "ERROR");
        System.out.println("minorWarning1: " + minorWarning1);
        System.out.println("minorWarning2: " + minorWarning2);
        System.out.println("majorWarning: " + majorWarning);
        System.out.println("majorError: " + majorError);
        System.out.println("minorWarning1, minorWarning1: " + minorWarning1.equals(minorWarning2));
        System.out.println("minorWarning2, minorWarning1: " + minorWarning2.equals(minorWarning1));
        System.out.println("minorWarning1, majorWarning: " + minorWarning1.equals(majorWarning));
        System.out.println("majorWarning, majorError: " + majorWarning.equals(majorError));

        System.out.println("minorWarning1.hashCode: " + minorWarning1.hashCode());
        System.out.println("minorWarning.hashCode2: " + minorWarning2.hashCode());
        System.out.println("majorWarning.hashCode: " + majorWarning.hashCode());
        System.out.println("majorError.hashCode: " + majorError.hashCode());*/

        {
            try {
                messageService.log(null, DOUBLES, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(ASC, (Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log((Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(null, (Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(DISTINCT,null, null, null, null);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(ASC, DOUBLES);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(ASC, DOUBLES, messageWithoutSeverity, messageEmpty);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(ASC);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(DOUBLES);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log();
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(null, DOUBLES, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(ASC, (Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log((Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(null, (Doubling) null, messageError);
            } catch (LogException e) {
                e.printStackTrace();
            }
            try {
                messageService.log(DESC, DISTINCT, null, regularMessage, null, messageWithoutSeverity, null, messageWithoutSeverity, messageWithoutSeverity, null);
            } catch (LogException e) {
                e.printStackTrace();
            }
        }
    }
}
