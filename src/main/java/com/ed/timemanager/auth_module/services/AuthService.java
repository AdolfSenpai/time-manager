package com.ed.timemanager.auth_module.services;

import com.ed.timemanager.auth_module.components.PasswordEncoder;
import com.ed.timemanager.auth_module.controllers.auth.requests.LoginRequest;
import com.ed.timemanager.auth_module.controllers.auth.requests.RegisterRequest;
import com.ed.timemanager.auth_module.controllers.auth.responses.AuthResponse;
import com.ed.timemanager.auth_module.exceptions.AuthException;
import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.auth_module.repositories.UserRepository;
import com.ed.timemanager.commons.components.JwtPrivateKey;
import com.ed.timemanager.commons.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    //region Constants

    @Value("${application.jwt-token-lifetime}")
    private static final Integer JWT_LIFETIME = 60*60*24*30;

    //endregion
    //region Fields

    private final UserRepository userRepository;

    private final JwtPrivateKey jwtKey;

    //endregion
    //region Public 

    public AuthResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        
        User user = Optional.ofNullable(this.userRepository.findByEmail(loginRequest.getEmail()))
            .orElseThrow(() -> new AuthException("Invalid email or password."));

        if (!PasswordEncoder.match(loginRequest.getPassword(), user.getPassword())) {

            throw new AuthException("Invalid email or password.");
        }

        String token = JwtUtil.generateToken(user.getId(), this.jwtKey.get(), JWT_LIFETIME);
        response.addCookie(this.createAuthCookie(token));

        return AuthResponse.builder()
            .id(user.getId().toString())
            .email(user.getEmail())
            .name(user.getName())
            .build();
    }

    public AuthResponse register(RegisterRequest registerRequest, HttpServletResponse response) {
        
        if (this.userRepository.findByEmail(registerRequest.getEmail()) != null) {

            throw new AuthException("User already exists.");
        }

        User user = User.builder()
            .email(registerRequest.getEmail())
            .name(registerRequest.getName())
            .password(PasswordEncoder.encode(registerRequest.getPassword()))
            .build();

        this.userRepository.save(user);

        String token = JwtUtil.generateToken(user.getId(), this.jwtKey.get(), JWT_LIFETIME);
        response.addCookie(this.createAuthCookie(token));

        return AuthResponse.builder()
            .id(user.getId().toString())
            .email(user.getEmail())
            .name(user.getName())
            .build();
    }
    
    //endregion
    //region Private

    private Cookie createAuthCookie(String token) {

        Cookie cookie = new Cookie("Authorization", token);

        cookie.setHttpOnly(false);
        cookie.setSecure(true);
        cookie.setDomain("local.edtmmngr.com");
        cookie.setMaxAge(AuthService.JWT_LIFETIME);
        cookie.setPath("/api");

        return cookie;
    }

    //endregion
}
