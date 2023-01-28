package com.ed.timemanager.task_module.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed.timemanager.commons.components.authorization_interceptor.Authorized;
import com.ed.timemanager.commons.controllers.AbstractControllerBase;
import com.ed.timemanager.commons.dto.PagedRequest;
import com.ed.timemanager.commons.dto.PagedResponse;
import com.ed.timemanager.task_module.dto.CreateTaskRequest;
import com.ed.timemanager.task_module.dto.TaskResponse;
import com.ed.timemanager.task_module.services.TaskService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TaskController extends AbstractControllerBase {
    //region Fields

    private final TaskService taskService;

    //endregion
    //region Public

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") String taskId) {

        TaskResponse response = this.taskService.getTask(taskId, this.getUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Authorized
    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
     
        List<TaskResponse> response = this.taskService.getAllTasks(getUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Authorized
    @PostMapping("/batch")
    public ResponseEntity<PagedResponse<TaskResponse>> getTaskBatch(@RequestBody PagedRequest request) {
        
        PagedResponse<TaskResponse> response = this.taskService.getTaskPage(request, getUser());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Authorized
    @PutMapping("/create")
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {

        return null;
    }



    @Authorized
    @GetMapping("/find")
    public ResponseEntity<TaskResponse> findTask(
        @RequestParam(value = "number", required = true) String taskNumber
    ) {

        return null;
    }

    //endregion
}
