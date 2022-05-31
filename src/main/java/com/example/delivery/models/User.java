package com.example.delivery.models;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.forms.CreateUserForm;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;


public record User(
        Optional<Long> id,
        String username
) {

    public static User of(
            @Nullable Long id,
            String username
    ) {
        return new User(
                Optional.ofNullable(id),
                checkNotNull(username)
        );
    }

    public static User fromEntity(UserEntity entity) {
        return User.of(
                entity.getId(),
                entity.getUsername()
        );
    }

    public static User fromForm(
            @Nullable Long id,
            CreateUserForm form
    ) {
        return User.of(
                id,
                form.username()
        );
    }
}
