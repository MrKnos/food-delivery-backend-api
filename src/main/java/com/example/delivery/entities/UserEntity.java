package com.example.delivery.entities;

import com.example.delivery.models.Role;
import com.example.delivery.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "USER_DETAILS")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "roles")
    private Set<RoleEntity> roles = new HashSet<>();

    public static UserEntity of(
            Long id,
            String username,
            String password,
            @Nullable Boolean enabled
    ) {
        final UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setEnabled(Optional.ofNullable(enabled).orElse(true));

        return entity;
    }

    public static UserEntity fromModel(User model) {
        return UserEntity.of(
                model.id().orElse(null),
                model.name(),
                "",
                true
        );
    }

    public void updateFrom(User model) {
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles.stream().map(Role::fromEntity).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
