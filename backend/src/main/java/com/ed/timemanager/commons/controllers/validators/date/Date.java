package com.ed.timemanager.commons.controllers.validators.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {

    String pattern() default "";

    String locale() default "";

    String message() default "{constraint.isNotDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
