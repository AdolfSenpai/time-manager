package com.ed.timemanager.commons.controllers;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ed.timemanager.auth_module.models.User;

import io.jsonwebtoken.ExpiredJwtException;

public abstract class AbstractControllerBase {
    //region Fields

    private final Logger logger = LoggerFactory.getLogger(AbstractControllerBase.class);

    //endregion
    //region Public

    @ExceptionHandler
    public String handleError(HttpServletRequest request, Exception e) {

        logger.error(e.getLocalizedMessage(), e);
        return e.getLocalizedMessage();
    }

    @ExceptionHandler
    public String handleTokenExpired(HttpServletRequest request, ExpiredJwtException e) {

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

        return AbstractControllerBase.getCurrentHttpRequest()
            .map(request -> request.getAttribute("currentUser"))
            .filter(User.class::isInstance)
            .map(User.class::cast)
            .orElse(null);
    }

    //endregion
}
