package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.services.MessageService;
import com.tcs.edu.validator.LogException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.tcs.edu.decorator.Doubling.DISTINCT;
import static com.tcs.edu.decorator.Doubling.DOUBLES;
import static com.tcs.edu.decorator.MessageOrder.ASC;
import static com.tcs.edu.decorator.MessageOrder.DESC;
import static com.tcs.edu.decorator.Severity.*;
import static org.junit.jupiter.api.Assertions.*;

public class SortMessageServiceTests {
    private static MessageService messageService;


    @BeforeAll
    static void precondition() {
        messageService = new com.tcs.edu.services.SortMessageService(new HashMapMessageRepository(), new TimestampMessageDecorator());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда messageOrder = null")
    public void checkValidationMessageOrderNull() {
        Message messageNotLogged = new Message("Не логируемое сообщение");
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log(null, DOUBLES, messageNotLogged));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда doubling = null")
    public void checkValidationDoublingNull() {
        Message messageNotLogged = new Message("Не логируемое сообщение");
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log(ASC, (Doubling) null, messageNotLogged));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда doubling = null при незаданном messageOrder")
    public void checkValidationDoublingNullWhenOrderEmpty() {
        Message messageNotLogged = new Message("Не логируемое сообщение");
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log((Doubling) null, messageNotLogged));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда messageOrder = null при незаданном doubling")
    public void checkValidationOrderNullWhenDoublingEmpty() {
        Message messageNotLogged = new Message("Не логируемое сообщение");
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log((MessageOrder) null, messageNotLogged));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда в Messages все месседжи = null")
    public void checkValidationMessagesNull() {
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log(ASC, DOUBLES, null, null));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда Messages не заданы")
    public void checkValidationMessagesEmpty() {
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log(DESC, DOUBLES));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка валидации, когда в Messages имеется message=''")
    public void checkValidationMessagesContainEmptyMessage() {
        Message messageNotLogged = new Message("Не логируемое сообщение");
        Message messageEmpty = new Message("");
        Throwable throwMessage = assertThrows(LogException.class, () -> messageService.log(ASC, DOUBLES, messageNotLogged, messageEmpty));
        assertEquals("notValidArgMessage", throwMessage.getMessage());
    }

    @Test
    @Tag("positive")
    @DisplayName("Проверка, что при повторных вызовах мессаджи записываются в коллекцию корректно")
    public void checkMessagesAfterDoubleLog() {
        Message messageMin = new Message(MINOR, "Message with MINOR severity");
        Message messageMaj = new Message(MAJOR, "Message with MAJOR severity");
        int sizeBefore = messageService.findAll().size();
        messageService.log(ASC, DOUBLES, messageMin);
        int sizeBetween = messageService.findAll().size();
        messageService.log(ASC, DOUBLES, messageMaj);
        int sizeAfter = messageService.findAll().size();
        assertEquals(sizeBefore + 1, sizeBetween, "Ожидается +1 элемент в коллекцию");
        assertEquals(sizeBefore + 2, sizeAfter, "Ожидается +2 элемента в коллекцию");
        checkMessageById(messageService, messageMin);
        checkMessageById(messageService, messageMaj);
    }

    @Test
    @Tag("positive")
    @DisplayName("Проверка, что при doubling = DOUBLES, сообщения с одинаковыми body не дедуплицируются")
    public void checkDoubleMessages() {
        Message message1 = new Message(MAJOR, "Message with MAJOR severity");
        Message message2 = new Message(MAJOR, "Message with MAJOR severity");
        int sizeBefore = messageService.findAll().size();
        messageService.log(ASC, message1, message2);
        int sizeAfter = messageService.findAll().size();
        assertEquals(sizeBefore + 2, sizeAfter, "Ожидается +2 элемента в коллекцию");
        checkMessageById(messageService, message1);
        checkMessageById(messageService, message2);
    }

    @Test
    @Tag("positive")
    @DisplayName("Проверка, что при doubling = DISTINCT, сообщения с одинаковыми body дедуплицируются")
    public void checkDistinctMessages() {
        String deduplicatedMessage = "Дедублицированное сообщение";
        Message messageMin = new Message(MINOR, deduplicatedMessage);
        Message messageReg = new Message(REGULAR, deduplicatedMessage);
        int sizeBefore = messageService.findAll().size();
        messageService.log(ASC, DISTINCT, messageMin, messageReg);
        int sizeAfter = messageService.findAll().size();
        assertEquals(sizeBefore + 1, sizeAfter);
        if (messageMin.getId() != null) {
            assertNull(messageReg.getId(),
                    "При дедубликации двух сообщений id должен сгенерироваться у одного и только одного сообщения");
            checkMessageById(messageService, messageMin);
        } else {
            assertNotNull(messageReg.getId(),
                    "При дедубликации двух сообщений id должен сгенерироваться у одного и только одного сообщения");
            checkMessageById(messageService, messageReg);
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Проверка, что при незаданном doubling, сообщения записываются без дедупликации")
    public void checkDefaultDoubling() {
        Message messageMin = new Message(REGULAR, "Message with REGULAR severity");
        Message messageReg = new Message(REGULAR, "Message with REGULAR severity");
        int sizeBefore = messageService.findAll().size();
        messageService.log(ASC, messageMin, messageReg);
        int sizeAfter = messageService.findAll().size();
        assertEquals(sizeBefore + 2, sizeAfter, "Ожидается 2 новых элемента в коллекции");
        checkMessageById(messageService, messageMin);
        checkMessageById(messageService, messageReg);
    }

    @Step("Проверка записи сообщения с корректными параметрами")
    private void checkMessageById(MessageService service, Message message) {
        assertNotNull(message.getId(), "требуется id сообщения");
        Message messageFromService = service.findByPrimaryKey(message.getId());
        assertNotNull(messageFromService, "Сообщение должно быть в коллекции");
        assertEquals(message.getSeverity(), messageFromService.getSeverity(), "Некорректный Severity");
        assertEquals(message.getBody(), messageFromService.getBody(), "Некорректный body");
    }
}
