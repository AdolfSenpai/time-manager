package com.ed.timemanager.auth_module.repositories;

import com.ed.timemanager.auth_module.models.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    
    @Query("FROM #{#entityName} WHERE email = :email")
    User findByEmail(@Param("email") String email);
}
