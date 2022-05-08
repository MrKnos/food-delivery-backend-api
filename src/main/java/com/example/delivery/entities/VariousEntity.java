package com.example.delivery.entities;

import com.example.delivery.models.Various;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Setter
@Table(name = "VARIOUS")
public class VariousEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private FoodOptionEntity option;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public static VariousEntity fromModel(Various various) {
        final VariousEntity entity = new VariousEntity();

        entity.setName(checkNotNull(various.name()));
        entity.setPrice(checkNotNull(various.price()));

        return entity;
    }
}
