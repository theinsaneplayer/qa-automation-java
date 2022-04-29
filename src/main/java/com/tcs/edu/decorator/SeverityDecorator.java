package com.tcs.edu.decorator;

public class SeverityDecorator {

    public static String mapToString(Severity severity) {
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
