package com.levik.shorturl.infa.api.error;

public class FieldErrorDetails {
    private final String name;
    private final String message;
    private final Object rejectedValue;

    public FieldErrorDetails(String name, String message, Object rejectedValue) {
        this.name = name;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public FieldErrorDetails(String name, String message) {
        this.name = name;
        this.message = message;
        this.rejectedValue = null;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}
