package com.ManageDrink.dto;

import com.ManageDrink.until.constant.CommonConstant;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToppingDTO {

    @Positive(message = "id must be a positive number")
    private Long id;

    @NotEmpty(message = "Topping name cannot be empty")
    private String name;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private int price;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }
    private int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_THOUSAND) * CommonConstant.ONE_THOUSAND;
    }

}
