package com.tcs.edu;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;

public class Application {
    public static void main(String[] args) {
        decorate("Hello world!");
        decorate("Hello world!");
        decorate("Hello world!");
    }
}
