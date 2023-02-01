package com.ed.timemanager.task_module.controllers.task.validators.get_task.task_id;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetTaskTaskIdValidator implements ConstraintValidator<GetTaskTaskId, String> {

    private final GetTaskTaskIdValidationCache cache;

    @SuppressWarnings("unused")
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {

            this.cache.setTaskId(UUID.fromString(value));
        }
        catch (IllegalArgumentException e) {

            return false;
        }

        return true;
    }
}
