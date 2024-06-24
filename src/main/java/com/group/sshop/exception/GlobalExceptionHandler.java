package com.group.sshop.exception;

import com.group.sshop.service.impl.DefaultUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler   {

    @ExceptionHandler(AuthenticationException.class)
    protected ModelAndView handleNoResourceFoundException(NoResourceFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

                if(request.getRequestURI().contains("/admin")){
                    return new ModelAndView("admin/error/not-found");
                } else {
                    return new ModelAndView("web/error/not-found");
                }
    }

}
