package com.levik.shorturl.infa.api.general;

import com.levik.shorturl.infa.api.error.ErrorDetailsDto;
import com.levik.shorturl.infa.api.error.FieldErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FIELD_VALIDATION = "Field validation";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exe,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String guid = UUID.randomUUID().toString();
        String url = getUrl(request);
        log.warn("Received request [{}, {}, {}] reason {}", guid, url,  status, exe.getMessage());

        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(status, FIELD_VALIDATION, guid);

        for (FieldError error : exe.getBindingResult().getFieldErrors()) {
            errorDetailsDto.addFieldError(new FieldErrorDetails(error.getField(), error.getDefaultMessage(), error.getRejectedValue()));
        }

        for (ObjectError error : exe.getBindingResult().getGlobalErrors()) {
            errorDetailsDto.addFieldError(new FieldErrorDetails(error.getObjectName() , error.getDefaultMessage()));
        }

        return handleExceptionInternal(
                exe, errorDetailsDto, headers, errorDetailsDto.getStatus(), request);
    }

    private String getUrl(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        }

        return "";
    }
}
