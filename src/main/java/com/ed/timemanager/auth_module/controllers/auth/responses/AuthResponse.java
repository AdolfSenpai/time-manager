package com.ed.timemanager.auth_module.controllers.auth.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
public class AuthResponse {

    private final String id;

    private final String name;

    private final String email;
}
