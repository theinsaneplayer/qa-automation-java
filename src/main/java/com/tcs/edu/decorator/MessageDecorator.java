package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

public interface MessageDecorator {
    Message decorate(Message message);
}
