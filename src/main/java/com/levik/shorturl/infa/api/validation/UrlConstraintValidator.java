package com.levik.shorturl.infa.api.validation;

import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlConstraintValidator implements ConstraintValidator<UrlConstraint, String> {

    private static final String[] DEFAULT_SCHEMES = new String[] {"http", "https"};

    private final UrlValidator urlValidator;

    public UrlConstraintValidator() {
        this.urlValidator = new UrlValidator(DEFAULT_SCHEMES, UrlValidator.ALLOW_LOCAL_URLS);
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotBlank(input) && urlValidator.isValid(input);
    }
}
