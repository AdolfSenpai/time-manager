package com.ed.timemanager.commons.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import com.ed.timemanager.commons.components.authorization_interceptor.RequestUser;
import com.ed.timemanager.commons.dto.ValidationErrors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ed.timemanager.auth_module.models.User;

import io.jsonwebtoken.ExpiredJwtException;

@RequiredArgsConstructor
@Component
public abstract class AbstractControllerBase {
    //region Fields

    private final RequestUser requestUser;

    private final Logger logger = LoggerFactory.getLogger(AbstractControllerBase.class);

    //endregion
    //region Public

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrors handleValidationErrors(BindException e) {

        final Map<String, String> fieldErrors = e.getFieldErrors().stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                error -> Optional.ofNullable(error.getDefaultMessage()).orElse("error")
            ));

        final List<String> globalErrors = e.getGlobalErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

        return new ValidationErrors(fieldErrors, globalErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationErrors(ConstraintViolationException e) {

        return e.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public String handleTokenExpired(ExpiredJwtException e) {

        return e.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String handleError(Exception e) {

        logger.error(e.getLocalizedMessage(), e);
        return e.getLocalizedMessage();
    }

    //endregion
    //region Protected static

    protected static Optional<HttpServletRequest> getCurrentHttpRequest() {

        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .filter(ServletRequestAttributes.class::isInstance)
            .map(ServletRequestAttributes.class::cast)
            .map(ServletRequestAttributes::getRequest);
    }

    //endregion
    //region Protected

    protected User getUser() {

        return this.requestUser.getUser();
    }

    //endregion
}
