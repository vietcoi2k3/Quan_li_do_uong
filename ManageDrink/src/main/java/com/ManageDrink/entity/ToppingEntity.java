package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = EntityConstant.TOPPING_NAME_TABLE)
public class ToppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String name;
    private int price;

    @Column(name = EntityConstant.DRINK_ENTITY_ID)
    private long drinkEntityID;
    @ManyToOne
    @JsonBackReference(value = "drink-topping")
    @JoinColumn(name = EntityConstant.DRINK_ENTITY_ID,updatable = false,insertable = false)
    private DrinkEntity drinkEntity;

}
