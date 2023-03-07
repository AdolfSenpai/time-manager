package com.ed.timemanager.auth_module.components;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@UtilityClass
public class PasswordEncoder {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encode(String password) {

        return encoder.encode(password);
    }

    public boolean match(String rawPassword, String encodedPassword) {

        return encoder.matches(rawPassword, encodedPassword);
    }
}
