package com.ManageDrink.dto;

import com.ManageDrink.until.constant.CommonConstant;
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

    @NotEmpty(message = "name drink cannot be empty")
    @Size(max = 50, message = "name drink must be less than 50 characters")
    private String nameDrink;

    @Size(max = 1000, message = "description must be less than 1000 characters")
    private String description;

    @Min(value = 0, message = "price must be equal or greater than zero")
    private int price;

    @NotNull(message = "create date cannot be null")
    @PastOrPresent(message = "create date must be a past or present date")
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    private List<Long> listIds;

    private List<String> toppingNames;


    public void setPrice(int price) {
        this.price = roundPrice(price);
    }

    private int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_THOUSAND) * CommonConstant.ONE_THOUSAND;
    }
}
