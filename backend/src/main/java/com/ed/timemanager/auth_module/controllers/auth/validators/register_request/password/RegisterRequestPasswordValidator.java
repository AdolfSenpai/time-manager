package com.ed.timemanager.auth_module.controllers.auth.validators.register_request.password;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterRequestPasswordValidator implements ConstraintValidator<RegisterRequestPassword, String> {
    
    private Pattern pattern;

    @Override
    public void initialize(RegisterRequestPassword annotation) {

        this.pattern = annotation.validation().getPattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        return this.pattern.matcher(value).matches();
    }
}