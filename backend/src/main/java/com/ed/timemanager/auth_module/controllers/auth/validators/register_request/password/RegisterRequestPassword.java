package com.ed.timemanager.auth_module.controllers.auth.validators.register_request.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RegisterRequestPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterRequestPassword {
    
    PasswordRegex validation() default PasswordRegex.LETTER_UPPER_LOWER_NUMBER_SPECIAL;

    String message() default "constraint.register.password";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
