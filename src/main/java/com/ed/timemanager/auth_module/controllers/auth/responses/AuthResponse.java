package com.ed.timemanager.auth_module.controllers.auth.responses;

import com.ed.timemanager.auth_module.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
public class AuthResponse {
    //region Fields

    private final String id;

    private final String name;

    private final String email;

    //endregion
    //region Static factories

    public static AuthResponse fromUser(User user) {

        return AuthResponse.builder()
            .id(user.getId().toString())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

    //endregion
}
