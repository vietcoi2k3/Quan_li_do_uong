package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = EntityConstant.TOPPING_NAME_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Topping name cannot be empty")
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String name;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private BigDecimal price;

    @Column(name = EntityConstant.DRINK_ENTITY_ID)
    private Long drinkEntityID;

    @ManyToOne()
    @JsonBackReference(value = "drink-topping")
    @JoinColumn(name = EntityConstant.DRINK_ENTITY_ID, updatable = false, insertable = false)
    private DrinkEntity drinkEntity;
}
