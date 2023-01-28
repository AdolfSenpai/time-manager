package com.ed.timemanager.task_module.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ed.timemanager.commons.models.BaseModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "task_time", schema="public")
@SuperBuilder(toBuilder = true)
public class TaskTime extends BaseModel {
    //region Fields

    @ManyToOne
    private final UserTask userTask;

    private final Instant startTime;

    private final Instant endTime;

    //endregion
}
