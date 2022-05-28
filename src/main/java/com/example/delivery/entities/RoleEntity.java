package com.example.delivery.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NaturalId
    private String roleName;

    private static RoleEntity of(
            Long id,
            String role
    ) {
        final RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setRoleName(role);

        return entity;
    }
}
