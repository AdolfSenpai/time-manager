package com.ed.timemanager.auth_module.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class RegisterRequest {

    private final String email;

    private final String password;
    
    private final String name;
}
