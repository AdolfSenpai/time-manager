package com.ed.timemanager.task_module.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTaskRequest {
    //region Fields

    private final String number;
    
    private final String name;
    
    private final String description;
    
    private final String link;

    //endregion
}
