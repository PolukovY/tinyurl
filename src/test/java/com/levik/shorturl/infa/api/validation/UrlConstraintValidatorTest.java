package com.levik.shorturl.infa.api.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class UrlConstraintValidatorTest {

    private UrlConstraintValidator testInstance;

    @BeforeEach
    void setUp() {
        testInstance = new UrlConstraintValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
                "https://www.google.com.ua/",
                "http://localhost:8080/test",
            })
    void shouldValidateCorrectUrl(String url) {
        boolean valid = testInstance.isValid(url, null);
        assertThat(valid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https:/www",
            "124",
            "ttps://www.google.com.ua",
    })
    void shouldValidateInCorrectUrl(String url) {
        boolean valid = testInstance.isValid(url, null);
        assertThat(valid).isFalse();
    }
}