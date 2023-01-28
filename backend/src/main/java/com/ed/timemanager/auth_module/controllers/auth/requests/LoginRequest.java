package com.ed.timemanager.auth_module.controllers.auth.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class LoginRequest {

    private final String email;

    private final String password;
}