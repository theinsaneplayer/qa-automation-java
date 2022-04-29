package com.tcs.edu.decorator;

/**
 * Класс присваивает уровень значимости
 * @author p.shatskov
 */

public class SeverityDecorator {

    /**
     * Метод навешивает уровень значимости
     * @author p.shatskov
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
