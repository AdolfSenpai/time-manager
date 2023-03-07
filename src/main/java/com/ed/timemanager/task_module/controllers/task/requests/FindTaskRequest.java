package com.ed.timemanager.task_module.controllers.task.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindTaskRequest {
    //region Fields

    private final String numberPrefix;

    private final String number;

    private final String name;

    private final String startDate;

    private final String endDate;

    //endregion
}
