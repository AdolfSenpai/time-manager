package com.ed.timemanager.task_module.controllers.task.validators.find_task;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FindTaskValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindTask {

    String message() default "{constraint.findTask.request}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
