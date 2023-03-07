package com.ed.timemanager.task_module.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.task_module.models.UserTask;

@Repository
public interface UserTaskRepository extends PagingAndSortingRepository<UserTask, UUID> {

    List<UserTask> findByUser(@Param("user") User user);

    Page<UserTask> findByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT count(u) FROM UserTask u WHERE u.user = :user AND u.task.id = :taskId")
    int taskBelongsToUser(@Param("user") User user, @Param("taskId") UUID taskID);
}
