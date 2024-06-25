package com.group.sshop.service;

import com.group.sshop.models.dto.ProfileForm;
import com.group.sshop.models.dto.RegisterFrom;
import com.group.sshop.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerNewUser(RegisterFrom registerRequest);

    boolean existByEmail(String s);

    User findById(Long id);

    void update(ProfileForm profileForm, Long id);
}
