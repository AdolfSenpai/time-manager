package com.ed.timemanager.auth_module.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ed.timemanager.auth_module.dto.LoginRequest;
import com.ed.timemanager.auth_module.dto.RegisterRequest;
import com.ed.timemanager.auth_module.exceptions.AuthException;
import com.ed.timemanager.auth_module.services.AuthService;
import com.ed.timemanager.commons.controllers.AbstractControllerBase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController extends AbstractControllerBase {
    //region Fields

    private final AuthService authService;

    @Value("${application.jwt-token-lifetime}")
    private final int jwtLifetime = 0;

    //endregion
    //region Public

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        
        String token = authService.login(loginRequest);


        response.addCookie(this.createAuthCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        
        String token = authService.register(registerRequest);

        response.addCookie(this.createAuthCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(value = { AuthException.class })
    public String handleAuthError(HttpServletRequest request, AuthException e) {
        
        return e.getLocalizedMessage();
    }

    //endregion
    //region Private

    private Cookie createAuthCookie(String token) {

        Cookie cookie = new Cookie("Authorization", token);

        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setDomain("edtmmngr.com");
        cookie.setMaxAge(this.jwtLifetime);
        cookie.setPath("/");

        return cookie;
    }

    //endregion
}
