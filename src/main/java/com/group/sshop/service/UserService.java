package com.group.sshop.service;

import com.group.sshop.models.dto.RegisterFrom;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void registerNewUser(RegisterFrom registerRequest);

    boolean existByEmail(String s);
}
