package com.ed.timemanager.task_module.controllers.task.validators.get_task.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GetTaskUserValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetTaskUser {

    String message() default "{constraint.getTask.user}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
