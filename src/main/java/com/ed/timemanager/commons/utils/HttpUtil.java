package com.ed.timemanager.commons.utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpUtil {
    
    public Optional<Cookie> getCookie(HttpServletRequest request, String cookieName) {

        List<Cookie> cookies = Optional.ofNullable(request.getCookies())
            .map(List::of)
            .orElseGet(Collections::emptyList);
        
        return cookies.stream()
            .filter(cookie -> cookie.getName().equals(cookieName))
            .findFirst();
    }
}
