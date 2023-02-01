package com.ed.timemanager.task_module.controllers.task.validators.get_task.task_id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class GetTaskTaskIdValidator implements ConstraintValidator<GetTaskTaskId, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {

            UUID.fromString(value);
        }
        catch (IllegalArgumentException e) {

            return false;
        }

        return true;
    }
}
