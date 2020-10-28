package com.levik.shorturl.infa.api;

import com.levik.shorturl.infa.api.general.BaseExceptionHandler;
import com.levik.shorturl.infa.repository.exception.ShortUrlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {

    private static final String REDIRECT = "redirect:%s";
    private static final String NOT_FOUND = "/404.html";

    @ExceptionHandler({ ShortUrlNotFoundException.class})
    public String notFoundException(ShortUrlNotFoundException exe) {
        String guid = UUID.randomUUID().toString();
        log.error("Received not found [{}] {}",guid, exe.getMessage());
        return String.format(REDIRECT, NOT_FOUND);
    }
}
