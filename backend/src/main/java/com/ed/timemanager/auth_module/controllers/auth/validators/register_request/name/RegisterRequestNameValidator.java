package com.ed.timemanager.auth_module.controllers.auth.validators.register_request.name;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterRequestNameValidator implements ConstraintValidator<RegisterRequestName, String> {
    
    private static final Pattern NAME_REGEX = Pattern.compile("^[A-Za-z\\d]{3,64}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        return RegisterRequestNameValidator.NAME_REGEX.matcher(value).matches();
    }
}
