package com.managedrink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.managedrink.until.PriceUtils.PriceUtils.roundPrice;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * DTO (Data Transfer Object) cho các thông tin về đồ uống.
 * Bao gồm các thuộc tính như id, tên, mô tả, giá và danh sách các ID của topping liên quan.
 */
public class DrinkDTO {

    @Schema(description = "ID của đồ uống", example = "1")
    private Long id;

    @Schema(description = "Tên đồ uống", example = "Cà phê sữa đá", required = true)
    @NotEmpty(message = "Tên đồ uống không được để trống")
    private String nameDrink;

    @Schema(description = "Mô tả đồ uống", example = "Đồ uống phổ biến vào buổi sáng", maxLength = 1000)
    private String description;

    @Schema(description = "Giá của đồ uống", example = "50000", minimum = "0")
    private int price;

    @Schema(description = "Danh sách các ID của topping liên quan đến đồ uống", example = "[1, 2, 3]")
    private List<Long> listIds;

    /**
     * Thiết lập giá của đồ uống và làm tròn giá thành số nguyên.
     *
     * @param price Giá của đồ uống
     */
    public void setPrice(int price) {
        this.price = roundPrice(price);
    }


}
