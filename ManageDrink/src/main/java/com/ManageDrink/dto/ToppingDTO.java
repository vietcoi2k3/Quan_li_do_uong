package com.ManageDrink.dto;

import com.ManageDrink.until.PriceUtils.PriceUtils;
import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.ValidateConstant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToppingDTO {

    private Long id;

    @NotEmpty(message = ValidateConstant.NAME_TOPPING_NOT_EMPTY)
    @NotNull(message = ValidateConstant.NAME_TOPPING_NOT_NULL)
    private String name;

    @PositiveOrZero(message = ValidateConstant.PRICE_TOPPING_MUST_GREATER_ZERO)
    private int price;

    public void setPrice(int price) {
        this.price = PriceUtils.roundPrice(price);
    }


}
