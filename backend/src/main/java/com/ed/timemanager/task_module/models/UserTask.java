package com.ed.timemanager.task_module.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ed.timemanager.auth_module.models.User;
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
@Table(name = "user_task", schema = "public")
@SuperBuilder(toBuilder = true)
public class UserTask extends BaseModel {
    //region Fields

    @ManyToOne
    private final User user;

    @ManyToOne
    private final Task task;

    //endregion
}
