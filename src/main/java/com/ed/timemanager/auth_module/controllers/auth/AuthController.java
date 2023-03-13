package com.ed.timemanager.auth_module.controllers.auth;

import com.ed.timemanager.auth_module.controllers.auth.requests.LoginRequest;
import com.ed.timemanager.auth_module.controllers.auth.requests.RegisterRequest;
import com.ed.timemanager.auth_module.controllers.auth.responses.AuthResponse;
import com.ed.timemanager.auth_module.exceptions.AuthException;
import com.ed.timemanager.auth_module.services.AuthService;
import com.ed.timemanager.commons.components.authorization_interceptor.Authorized;
import com.ed.timemanager.commons.components.authorization_interceptor.RequestUser;
import com.ed.timemanager.commons.controllers.AbstractControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class AuthController extends AbstractControllerBase {
    //region Fields

    private final AuthService authService;

    //endregion
    //region Ctor

    @Autowired
    public AuthController(RequestUser requestUser, AuthService authService) {

        super(requestUser);
        this.authService = authService;
    }

    //endregion
    //region Public

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        AuthResponse user = authService.login(loginRequest, response);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
        @Valid @RequestBody RegisterRequest registerRequest,
        HttpServletResponse response
    ) {
        AuthResponse user = authService.register(registerRequest, response);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Authorized
    @GetMapping("/currentUser")
    public ResponseEntity<AuthResponse> currentUser() {

        return new ResponseEntity<>(AuthResponse.fromUser(this.getUser()), HttpStatus.OK);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthError(AuthException e) {

        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    //endregion
}
