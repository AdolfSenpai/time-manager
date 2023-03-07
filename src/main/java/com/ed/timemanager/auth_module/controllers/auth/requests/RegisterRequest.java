package com.ed.timemanager.auth_module.controllers.auth.requests;

import javax.validation.constraints.Email;
import com.ed.timemanager.auth_module.controllers.auth.validators.register_request.name.RegisterRequestName;
import com.ed.timemanager.auth_module.controllers.auth.validators.register_request.password.RegisterRequestPassword;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class RegisterRequest {

    @Email(message = "{constraint.register.email}")
    private final String email;

    @RegisterRequestPassword
    private final String password;
    
    @RegisterRequestName
    private final String name;
}
