package com.group.sshop.models.entities;

import com.group.sshop.models.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Image avatar;
    private String email;
    private String fullName;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private Timestamp verifiedAt;
    private UserStatus status;
}
