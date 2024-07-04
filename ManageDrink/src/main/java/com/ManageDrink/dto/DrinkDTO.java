package com.ManageDrink.dto;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.MessageConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static com.ManageDrink.until.PriceUtils.PriceUtils.roundPrice;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkDTO {

    @Positive(message = MessageConstant.ID_MUST_BE_POSITIVE)
    private Long id;

    @NotEmpty(message = MessageConstant.NAME_NOT_EMPTY)
    @Size(max = 50, message = MessageConstant.NAME_SIZE_50)
    private String nameDrink;

    @Size(max = 1000, message = MessageConstant.DESCRIPTION_SIZE)
    private String description;

    @NotNull(message = MessageConstant.PRICE_NOT_NULL)
    @Min(value = 0, message = MessageConstant.PRICE_MIN)
    private int price;

    @NotNull(message = MessageConstant.CREATE_DATE_NOT_NULL)
    @PastOrPresent(message = MessageConstant.CREATE_DATE_PAST_OR_PRESENT)
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    private List<Long> listIds;

    private List<String> toppingNames;


    public void setPrice(int price) {
        this.price = roundPrice(price);
    }


}
