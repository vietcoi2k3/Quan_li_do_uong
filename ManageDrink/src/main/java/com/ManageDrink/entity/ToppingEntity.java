package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.ManageDrink.until.constant.MessageConstant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

import static com.ManageDrink.until.PriceUtils.PriceUtils.roundPrice;

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

    @NotEmpty(message = MessageConstant.NAME_NOT_EMPTY)
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String name;

    @NotNull(message = MessageConstant.PRICE_NOT_NULL)
    @Min(value = 0, message = MessageConstant.PRICE_MIN)
    @Column(columnDefinition = CommonConstant.INT)
    private int price;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }


}
