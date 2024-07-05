package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.ManageDrink.until.constant.ValidateConstant;
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

    @NotEmpty(message = ValidateConstant.NAME_TOPPING_NOT_EMPTY)
    @NotNull(message = ValidateConstant.NAME_TOPPING_NOT_NULL)
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String name;

    @PositiveOrZero(message = ValidateConstant.PRICE_TOPPING_MUST_GREATER_ZERO)
    private int price;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }

    private int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_HUNDRED) * CommonConstant.ONE_HUNDRED;
    }
}
