package com.example.delivery.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TAG")
public class TagEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(
            mappedBy = "tags",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private Set<RestaurantEntity> restaurants = new HashSet<>();

    @NaturalId
    private String name;

    public static TagEntity of(String name) {
        final TagEntity entity = new TagEntity();
        entity.setName(name);

        return entity;
    }
}
