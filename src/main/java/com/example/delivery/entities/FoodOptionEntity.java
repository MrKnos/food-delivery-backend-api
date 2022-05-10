package com.example.delivery.entities;

import com.example.delivery.models.FoodOption;
import com.example.delivery.models.OptionSelectionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Setter
@Table(name = "FOOD_OPTION")
public class FoodOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private FoodEntity food;

    @Column(name = "name")
    private String name;

    @Column(name = "selection_type")
    private OptionSelectionType selectionType;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<VariousEntity> various;

    public static FoodOptionEntity of(
            Long id,
            FoodEntity food,
            String name,
            OptionSelectionType selectionType,
            List<VariousEntity> various
    ) {
        final FoodOptionEntity entity = new FoodOptionEntity();
        entity.setId(id);
        entity.setFood(food);
        entity.setName(name);
        entity.setSelectionType(selectionType);
        entity.setVarious(various);

        return entity;
    }

    public static FoodOptionEntity fromModel(FoodOption option) {
        final FoodOptionEntity entity = FoodOptionEntity.of(
                null,
                null,
                checkNotNull(option.name()),
                checkNotNull(option.type()),
                checkNotNull(option.various())
                        .stream()
                        .map(VariousEntity::fromModel)
                        .collect(Collectors.toList())
        );

        entity.getVarious().forEach(various -> various.setOption(entity));

        return entity;
    }
}
