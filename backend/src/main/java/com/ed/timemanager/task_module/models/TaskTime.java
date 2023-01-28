package com.ed.timemanager.task_module.models;

import com.ed.timemanager.commons.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
public class TaskTime extends BaseModel {
    //region Fields

    @ManyToOne
    private final UserTask userTask;

    private final Instant startTime;

    private final Instant endTime;

    //endregion
}
