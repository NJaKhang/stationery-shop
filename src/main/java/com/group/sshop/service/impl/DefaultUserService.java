package com.group.sshop.service.impl;

import com.group.sshop.models.dto.ProfileForm;
import com.group.sshop.models.dto.RegisterFrom;
import com.group.sshop.models.entities.User;
import com.group.sshop.models.enums.UserRole;
import com.group.sshop.models.enums.UserStatus;
import com.group.sshop.repository.UserRepository;
import com.group.sshop.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void registerNewUser(RegisterFrom registerFrom) {
        User user = User.builder()
                .password(passwordEncoder.encode(registerFrom.getPassword()))
                .email(registerFrom.getEmail())
                .role(UserRole.USER)
                .status(UserStatus.ENABLED)
                .verifiedAt(null)
                .fullName(registerFrom.getFullName())
                .build();
        user = userRepository.save(user);
    }

    @Override
    public boolean existByEmail(String s) {
        return userRepository.existsByEmail(s);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(RuntimeException::new);
    }

    @Override
    public void update(ProfileForm profileForm, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFullName(profileForm.getFullName());
        user.setEmail(profileForm.getEmail());
        userRepository.save(user);
    }
}
