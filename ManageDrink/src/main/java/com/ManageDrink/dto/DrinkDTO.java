package com.ManageDrink.dto;

import com.ManageDrink.until.PriceUtils.PriceUtils;
import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.ValidateConstant;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkDTO {

    private Long id;

    @NotEmpty(message = ValidateConstant.NAME_DRINK_NOT_EMPTY)
    @NotNull(message =ValidateConstant.NAME_DRINK_NOT_NULL)
    @Size(max = 50, message = ValidateConstant.NAME_DRINK_MAX_SIZE)
    private String nameDrink;

    @Size(max = 1000, message = ValidateConstant.DESCRIPTION_DRINK_MAX_SIZE)
    private String description;

    @Min(value = 0, message = ValidateConstant.PRICE_DRINK_MUST_GREATER_ZERO)
    private int price;

    private List<Long> listIds;

    public void setPrice(int price) {
        this.price = PriceUtils.roundPrice(price);
    }
}
