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

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
public class Task extends BaseModel {
    //region Fields

    private final String numberPrefix;

    private final String number;
    
    private final String name;
    
    private final String description;
    
    private final String link;

    @ManyToOne
    private final TaskGroup group;

    //endregion
}
