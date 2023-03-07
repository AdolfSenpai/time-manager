package com.ed.timemanager.task_module.models;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.commons.models.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
public class UserTask extends BaseModel {
    //region Fields

    @ManyToOne
    private final User user;

    @ManyToOne
    private final Task task;

    //endregion
}
