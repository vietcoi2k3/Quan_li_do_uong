package com.ManageDrink.dto;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.ValidateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
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
        this.price = roundPrice(price);
    }

    private int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_HUNDRED) * CommonConstant.ONE_HUNDRED;
    }
}
