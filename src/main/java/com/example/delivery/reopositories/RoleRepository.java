package com.example.delivery.reopositories;

import com.example.delivery.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Boolean existsByRoleName(String name);

    Optional<RoleEntity> findByRoleName(String name);

}
