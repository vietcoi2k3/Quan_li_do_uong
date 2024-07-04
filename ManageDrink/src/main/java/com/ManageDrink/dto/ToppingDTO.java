package com.ManageDrink.dto;

import com.ManageDrink.until.constant.MessageConstant;
import jakarta.validation.constraints.*;
import lombok.*;

import static com.ManageDrink.until.PriceUtils.PriceUtils.roundPrice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToppingDTO {

    @Positive(message = MessageConstant.ID_MUST_BE_POSITIVE)
    private Long id;

    @NotEmpty(message = MessageConstant.NAME_NOT_EMPTY)
    @Size(max = 50, message = MessageConstant.NAME_SIZE_50)
    private String name;

    @NotNull(message = MessageConstant.PRICE_NOT_NULL)
    @Min(value = 0, message = MessageConstant.PRICE_MIN)
    private int price;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }


}
