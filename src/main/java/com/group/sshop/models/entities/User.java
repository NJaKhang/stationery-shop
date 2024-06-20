package com.group.sshop.models.entities;

import com.group.sshop.models.enums.UserRole;
import com.group.sshop.models.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Image avatar;
    private String email;
    private String fullName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Timestamp verifiedAt;
    private UserStatus status;
}
