package com.ed.timemanager.task_module.controllers.task;

import com.ed.timemanager.task_module.controllers.task.validators.get_task.task_id.GetTaskTaskIdValidationCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class TaskControllerValidationsCache {

    private final GetTaskTaskIdValidationCache getTaskTaskIdValidationCache;
}
