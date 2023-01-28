package com.ed.timemanager.task_module.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.commons.dto.PagedRequest;
import com.ed.timemanager.commons.dto.PagedResponse;
import com.ed.timemanager.task_module.dto.TaskResponse;
import com.ed.timemanager.task_module.models.Task;
import com.ed.timemanager.task_module.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskService {
    //region Fields

    private final TaskRepository taskRepository;

    //endregion
    //region Public static

    public static TaskResponse createTaskResponseBy(Task task) {
        
        return TaskResponse.builder()
            .id(task.getId().toString())
            .number(task.getNumberPrefix() + task.getNumber())
            .link(task.getLink())
            .name(task.getName())
            .description(task.getDescription())
            .build();
    }

    //endregion
    //region Public

    public TaskResponse getTask(String taskId, User user) {

        return this.taskRepository.findById(UUID.fromString(taskId))
            .map(TaskService::createTaskResponseBy)
            .orElse(null);
    }

    public List<TaskResponse> getAllTasks(User user) {

        return null;
    }

    public PagedResponse<TaskResponse> getTaskPage(PagedRequest request, User user) {

        return null;
    }

    //endregion
}
