package com.ed.timemanager.commons.components.authorization_interceptor;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ed.timemanager.auth_module.models.User;
import com.ed.timemanager.auth_module.repositories.UserRepository;
import com.ed.timemanager.commons.components.JwtPrivateKey;
import com.ed.timemanager.commons.utils.HttpUtil;
import com.ed.timemanager.commons.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    //region Fields

    private final UserRepository userRepository;

    private final JwtPrivateKey jwtKey;

    private final RequestUser requestUser;

    //endregion
    //region Public

    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler
    ) throws Exception {

        boolean dontInterrupt = true;

        if (handler instanceof HandlerMethod) {
            
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            
            if (method.isAnnotationPresent(Authorized.class)) {

                String token = HttpUtil.getCookie(request, "Authorization")
                    .map(Cookie::getValue)
                    .orElseGet(() -> request.getHeader("Authorization"));

                if (token != null && !token.isEmpty()) {
                    
                    UUID userId = JwtUtil.getUserIdFromToken(token, jwtKey.get());
                    Optional<User> user = userRepository.findById(userId);
                    
                    if (user.isEmpty()) {
                        
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid Token"
                        );
                        dontInterrupt = false;
                    }
                    else {

                        this.requestUser.setUser(user.get());
                    }
                }
                else {
                    
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "Token not found in request headers"
                    );
                    dontInterrupt = false;
                }
            }
        }

        return dontInterrupt;
    }

    //endregion
}
