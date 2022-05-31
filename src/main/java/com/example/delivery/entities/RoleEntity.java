package com.example.delivery.entities;

import com.example.delivery.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(
            mappedBy = "roles",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private Set<UserEntity> userDetails = new HashSet<>();

    public static RoleEntity of(
            Long id,
            String role
    ) {
        final RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setRoleName(role);

        return entity;
    }

    public static RoleEntity fromModel(Role role) {
        return RoleEntity.of(null, role.name());
    }
}
