package com.example.delivery.entities;

import com.example.delivery.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "APP_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public static UserEntity of(
            Long id,
            String name
    ) {
        final UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setName(name);

        return entity;
    }

    public static UserEntity fromModel(User model) {
        return UserEntity.of(
                model.id().orElse(null),
                model.name()
        );
    }

    public void updateFrom(User model) {
        setName(model.name());
    }
}
