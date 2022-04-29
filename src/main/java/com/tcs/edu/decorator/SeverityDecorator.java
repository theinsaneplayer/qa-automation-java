package com.tcs.edu.decorator;

public class SeverityDecorator {

    /**
     * Класс присваивает уровень значимости
     * @param severity уровни значимости
     * @return уровень значимости в зависимости от запроса
     */

    public static String getSeverityValueByType(Severity severity) {
        String severityString;
        switch (severity) {
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "!";
                break;
            case MAJOR:
                severityString = "!!!";
                break;
            default:
                severityString = "UNDEF";
        }
        return severityString;
    }
}
