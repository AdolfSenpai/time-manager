package com.ed.timemanager.auth_module.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ed.timemanager.auth_module.components.PasswordEncoder;
import com.ed.timemanager.auth_module.controllers.auth.requests.LoginRequest;
import com.ed.timemanager.auth_module.controllers.auth.requests.RegisterRequest;
import com.ed.timemanager.auth_module.exceptions.AuthException;
import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.auth_module.repositories.UserRepository;
import com.ed.timemanager.commons.components.JwtPrivateKey;
import com.ed.timemanager.commons.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    //region Constants

    @Value("${application.jwt-token-lifetime}")
    private static final Integer JWT_LIFETIME = 0;

    //endregion
    //region Fields

    private final UserRepository userRepository;

    private final JwtPrivateKey jwtKey;

    //endregion
    //region Public 

    public User login(LoginRequest loginRequest) {
        
        User user = Optional.ofNullable(this.userRepository.findByEmail(loginRequest.getEmail()))
            .orElseThrow(() -> new AuthException("Invalid email or password."));

        if (!PasswordEncoder.match(loginRequest.getPassword(), user.getPassword())) {

            throw new AuthException("Invalid email or password.");
        }

        return user.toBuilder()
            .token(JwtUtil.generateToken(user.getId(), this.jwtKey.get(), JWT_LIFETIME))
            .build();
    }

    public User register(RegisterRequest registerRequest) {
        
        if (this.userRepository.findByEmail(registerRequest.getEmail()) != null) {

            throw new AuthException("User already exists.");
        }

        User user = User.builder()
            .email(registerRequest.getEmail())
            .name(registerRequest.getName())
            .password(PasswordEncoder.encode(registerRequest.getPassword()))
            .build();

        this.userRepository.save(user);

        return user.toBuilder()
            .token(JwtUtil.generateToken(user.getId(), this.jwtKey.get(), JWT_LIFETIME))
            .build();
    }
    
    //endregion
}
