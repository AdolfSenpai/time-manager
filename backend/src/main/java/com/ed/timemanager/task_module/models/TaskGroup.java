package com.ed.timemanager.task_module.models;

import com.ed.timemanager.commons.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
public class TaskGroup extends BaseModel {
    //region Fields

    private final String name;

    @OneToMany
    private final List<Task> taskList;

    //endregion
}
