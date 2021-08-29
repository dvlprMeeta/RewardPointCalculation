package com.infogain.retailer.security;

import com.infogain.retailer.exception.RetailerAuthenticationException;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @SneakyThrows
    @Override
    public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2) {
        throw new RetailerAuthenticationException(arg2.getMessage());
    }

}