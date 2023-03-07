package com.ed.timemanager.commons.controllers.validators.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateValidator implements ConstraintValidator<Date, String> {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_INSTANT;

    private static final String DEFAULT_LOCALE = "ru";

    private String pattern;

    private String locale;

    @Override
    public void initialize(Date constraintAnnotation) {

        ConstraintValidator.super.initialize(constraintAnnotation);
        pattern = constraintAnnotation.pattern();
        locale = constraintAnnotation.locale();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {

            DateTimeFormatter formatter = DEFAULT_FORMATTER;

            if (pattern != null) {

                if (locale != null) {

                    formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag(locale));
                }
                else {

                    formatter = DateTimeFormatter.ofPattern(pattern);
                }
            }

            LocalDateTime.parse(value, formatter);
        }
        catch (DateTimeException e) {

            return false;
        }

        return true;
    }
}
