package com.managedrink.entity;

import com.managedrink.until.constants.CommonConstant;
import com.managedrink.until.constants.EntityConstant;
import com.managedrink.until.constants.ValidateConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.managedrink.until.PriceUtils.PriceUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = EntityConstant.TOPPING_NAME_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * Đối tượng biểu diễn một loại topping cho đồ uống.
 */
public class ToppingEntity {

    /**
     * ID của topping.
     *
     * Được tự động sinh ra bằng chiến lược GenerationType.IDENTITY.
     * Sử dụng @JsonIgnore để tránh trường hợp set ID từ bên ngoài.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // tránh trường hợp set id
    private Long id;

    /**
     * Tên của topping, không được rỗng và không được null.
     *
     * Cột được định nghĩa với kiểu VARCHAR(50).
     */
    @NotEmpty(message = ValidateConstant.NAME_TOPPING_NOT_EMPTY)
    @NotNull(message = ValidateConstant.NAME_TOPPING_NOT_NULL)
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String name;

    /**
     * Giá của topping, phải là số dương hoặc bằng 0.
     *
     * Giá trị được làm tròn bằng phương thức roundPrice.
     */
    @PositiveOrZero(message = ValidateConstant.PRICE_TOPPING_MUST_GREATER_ZERO)
    private int price;

    /**
     * Setter cho giá của topping, làm tròn giá trị sử dụng phương thức roundPrice.
     * @param price Giá của topping trước khi làm tròn
     */
    public void setPrice(int price) {
        this.price = PriceUtils.roundPrice(price);
    }

}
