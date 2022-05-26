package com.example.delivery.models;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.forms.UserForm;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;


public record User(
        Optional<Long> id,
        String name
) {

    public static User of(
            @Nullable Long id,
            String name
    ) {
        return new User(
                Optional.ofNullable(id),
                checkNotNull(name)
        );
    }

    public static User fromEntity(UserEntity entity) {
        return User.of(
                entity.getId(),
                entity.getName()
        );
    }

    public static User fromForm(UserForm form) {
        return User.of(
                null,
                form.name()
        );
    }
}
