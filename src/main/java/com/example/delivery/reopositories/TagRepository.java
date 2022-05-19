package com.example.delivery.reopositories;

import com.example.delivery.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByName(String name);

    Boolean existsByName(String name);

}
