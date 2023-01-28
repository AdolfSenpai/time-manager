package com.ed.timemanager.auth_module.controllers.auth.validators.register_request.name;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RegisterRequestNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterRequestName {
    
    String message() default "constraint.register.name";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
