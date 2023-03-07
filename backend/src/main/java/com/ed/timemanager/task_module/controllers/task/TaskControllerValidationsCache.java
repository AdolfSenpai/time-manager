package com.ed.timemanager.task_module.controllers.task;

import com.ed.timemanager.task_module.controllers.task.validators.get_task.task_id.ValidationCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Component
public class TaskControllerValidationsCache {

    private final ValidationCache<UUID> taskId;
}
