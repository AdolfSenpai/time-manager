package com.ed.timemanager.task_module.models;

import javax.persistence.Entity;
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
@Table(name = "task", schema = "public")
@SuperBuilder(toBuilder = true)
public class Task extends BaseModel {
    //region Fields

    private final String numberPrefix;

    private final int number;
    
    private final String name;
    
    private final String description;
    
    private final String link;

    //endregion
}
