package com.ed.timemanager.task_module.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.task_module.models.UserTask;

@Repository
public interface UserTaskRepository extends CrudRepository<UserTask, UUID> {
   
    @Query("FROM #{#entityName} WHERE user = :user")
    List<UserTask> findByUser(@Param("user") User user);
}
