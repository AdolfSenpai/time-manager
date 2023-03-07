package com.ed.timemanager.task_module.controllers.task.validators.get_task.user;

import com.ed.timemanager.commons.components.authorization_interceptor.RequestUser;
import com.ed.timemanager.task_module.controllers.task.validators.get_task.task_id.ValidationCache;
import com.ed.timemanager.task_module.repositories.UserTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetTaskUserValidator implements ConstraintValidator<GetTaskUser, String> {

    private final UserTaskRepository userTaskRepository;

    private final ValidationCache<UUID> validationCache;

    private final RequestUser requestUser;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return this.userTaskRepository
            .taskBelongsToUser(this.requestUser.getUser(), this.validationCache.getValue()) > 0;
    }
}
