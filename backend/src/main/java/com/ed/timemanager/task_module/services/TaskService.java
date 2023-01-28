package com.ed.timemanager.task_module.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.commons.dto.PagedResponse;
import com.ed.timemanager.task_module.controllers.task.requests.CreateTaskRequest;
import com.ed.timemanager.task_module.controllers.task.responses.TaskResponse;
import com.ed.timemanager.task_module.models.Task;
import com.ed.timemanager.task_module.models.UserTask;
import com.ed.timemanager.task_module.repositories.TaskRepository;
import com.ed.timemanager.task_module.repositories.UserTaskRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskService {
    //region Fields

    private final TaskRepository taskRepository;

    private final UserTaskRepository userTaskRepository;

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

        return this.userTaskRepository.findByUser(user).stream()
            .map(UserTask::getTask)
            .map(TaskService::createTaskResponseBy)
            .collect(Collectors.toList());
    }

    public PagedResponse<TaskResponse> getTaskPage(int pageNumber, int pageSize, User user) {

        Page<Task> page = this.taskRepository.findAll(PageRequest.of(pageNumber, pageSize));
        
        return PagedResponse.<TaskResponse>builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .pageCount(page.getTotalPages())
            .responseList(
                page.getContent().stream()
                    .map(TaskService::createTaskResponseBy)
                    .collect(Collectors.toList())
            )
            .build();
    }

    public TaskResponse createTask(CreateTaskRequest request, User user) {
        
        Task task = Task.builder()
            .numberPrefix(request.getNumberPrefix())
            .number(request.getNumber())
            .name(request.getName())
            .link(request.getLink())
            .description(request.getDescription())
            .build();

        UserTask userTask = UserTask.builder()
            .task(task)
            .user(user)
            .build();

        return TaskService.createTaskResponseBy(this.saveNewTask(task, userTask));
    }

    //endregion
    //region Private

    @Transactional
    private Task saveNewTask(Task task, UserTask userTask) {

        Task createdTask = this.taskRepository.save(task);
        this.userTaskRepository.save(userTask);

        return createdTask;
    }

    //endregion
}
