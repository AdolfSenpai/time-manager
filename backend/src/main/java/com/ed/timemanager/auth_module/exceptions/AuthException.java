package com.ed.timemanager.auth_module.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AuthException extends RuntimeException {
    //region

    public AuthException(String message, Throwable cause) {

        super(message, cause);
    }
    
    public AuthException(String message) {

        super(message);
    }

    //endregion
}
