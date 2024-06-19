package com.group.sshop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        StringBuilder url = new StringBuilder(request.getContextPath()).append("/");
        for (var authority : authentication.getAuthorities()){
            if (authority.getAuthority().equals("ADMIN")){
                url.append("admin");
            }
        }
        response.sendRedirect(url.toString());
    }
}
