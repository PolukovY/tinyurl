package com.levik.shorturl.infa.api.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorDetailsDto {
    private final HttpStatus status;
    private final int code;
    private final String generalMessage;
    private final String id;
    private final List<FieldErrorDetails> fieldErrors;

    public ErrorDetailsDto(HttpStatus status, String generalMessage, String id) {
        this.status = status;
        this.generalMessage = generalMessage;
        this.id = id;
        this.code = status.value();
        this.fieldErrors = new ArrayList<>();
    }

    public void addFieldError(FieldErrorDetails fieldErrorDetails) {
        this.fieldErrors.add(fieldErrorDetails);
    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public String getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<FieldErrorDetails> getFieldErrors() {
        return fieldErrors;
    }

    public int getCode() {
        return code;
    }
}
