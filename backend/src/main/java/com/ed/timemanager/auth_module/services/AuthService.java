package com.ed.timemanager.auth_module.services;

import org.springframework.stereotype.Service;

import com.ed.timemanager.auth_module.components.PasswordEncoder;
import com.ed.timemanager.auth_module.dto.AuthResponse;
import com.ed.timemanager.auth_module.dto.LoginRequest;
import com.ed.timemanager.auth_module.dto.RegisterRequest;
import com.ed.timemanager.auth_module.exceptions.AuthException;
import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.auth_module.repositories.UserRepository;
import com.ed.timemanager.commons.components.JwtPrivateKey;
import com.ed.timemanager.commons.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    //region Fields

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtPrivateKey jwtKey;

    //endregion
    //region Public 

    public AuthResponse login(LoginRequest loginRequest) {
        
        User user = this.userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new AuthException("Invalid email or password."));

        if (!passwordEncoder.match(loginRequest.getPassword(), user.getPassword())) {

            throw new AuthException("Invalid email or password.");
        }

        String token = JwtUtil.generateToken(user.getId(), this.jwtKey.get());
        
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        
        if (this.userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {

            throw new AuthException("User already exists.");
        }

        User user = User.builder()
            .email(registerRequest.getEmail())
            .name(registerRequest.getName())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .build();

        this.userRepository.save(user);

        String token = JwtUtil.generateToken(user.getId(), this.jwtKey.get());
        
        return AuthResponse.builder().token(token).build();
    }
    
    //endregion
}
