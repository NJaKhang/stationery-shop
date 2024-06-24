package com.group.sshop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String error = "";
        if (exception instanceof UsernameNotFoundException) {
            error = "userNotFound";

        } else if (exception instanceof BadCredentialsException) {
            error = "badCredentials";
        } else if (exception instanceof LockedException) {
            error = "locked";

        } else if (exception instanceof DisabledException) {
            error = "disabled";

        } else if (exception instanceof AuthenticationServiceException) {
            error = "badCredentials";

        } else {

        }
        System.out.println(exception);
        response.sendRedirect(request.getContextPath() + "/login?error=" + error);

    }
}
