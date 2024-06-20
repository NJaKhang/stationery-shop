package com.group.sshop.models.domain;

import com.group.sshop.models.entities.User;
import com.group.sshop.models.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Data
@Builder
public class Principal implements UserDetails {
    private Long id;
    private String email;
    private Boolean isVerified;
    private String fullName;
    private String password;
    private UserStatus status;
    private Collection<? extends GrantedAuthority> authorities;

    public static Principal create(User user){
        return Principal.builder()
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.getStatus())
                .fullName(user.getFullName())
                .isVerified(user.getVerifiedAt() != null)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !Objects.equals(status, UserStatus.EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Objects.equals(status, UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(status, UserStatus.ENABLED) && isVerified;
    }
}
