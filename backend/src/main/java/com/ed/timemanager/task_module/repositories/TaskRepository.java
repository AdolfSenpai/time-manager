package com.ed.timemanager.task_module.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ed.timemanager.task_module.models.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, UUID> {
   
    @Query("FROM #{#entityName} WHERE numberPrefix = :prefix AND number = :number")
    Task findByNumber(
        @Param("prefix") String numberPrefix,
        @Param("number") int number
    );
}
