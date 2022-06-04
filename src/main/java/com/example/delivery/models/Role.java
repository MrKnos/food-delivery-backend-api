package com.example.delivery.models;

import com.example.delivery.entities.RoleEntity;
import com.example.delivery.exceptions.UnknownValueException;
import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;

public record Role(String name) implements GrantedAuthority {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    public static final ImmutableList<String> allRoles = ImmutableList.of(
            Role.USER, Role.ADMIN
    );

    public static Role fromEntity(RoleEntity entity) {
        return new Role(entity.getRoleName());
    }

    public static Role fromName(String roleName) {
        if (!allRoles.contains(roleName)) {
            throw new UnknownValueException(Role.class, roleName);
        }

        return new Role(roleName);
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
