package com.example.delivery.models;

import com.example.delivery.entities.RoleEntity;
import org.springframework.security.core.GrantedAuthority;

public record Role(String authority) implements GrantedAuthority {

    public static final String USER = "USER";
    public static final String AUTHOR = "AUTHOR";

    public static Role fromEntity(RoleEntity entity) {
        return new Role(entity.getRoleName());
    }

    @Override
    public String getAuthority() {
        return authority();
    }
}
