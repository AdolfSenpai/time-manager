package com.ed.timemanager.task_module.controllers.task.validators.find_task;

import com.ed.timemanager.task_module.controllers.task.requests.FindTaskRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FindTaskValidator implements ConstraintValidator<FindTask, FindTaskRequest> {

    @Override
    public boolean isValid(FindTaskRequest value, ConstraintValidatorContext context) {

        boolean result = true;

        final String startDate = value.getStartDate();
        final String endDate = value.getEndDate();

        if (startDate != null && endDate != null) {


        }

        return result;
    }
}
