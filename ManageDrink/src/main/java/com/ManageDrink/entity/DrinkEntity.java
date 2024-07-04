package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.ManageDrink.until.constant.MessageConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static com.ManageDrink.until.PriceUtils.PriceUtils.roundPrice;

@Entity
@Table(name = EntityConstant.DRINK_NAME_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = MessageConstant.NAME_NOT_EMPTY)
    @Size(max = 50, message = MessageConstant.NAME_SIZE_50)
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String nameDrink;

    @Size(max = 1000, message = MessageConstant.DESCRIPTION_SIZE)
    @Column(columnDefinition = CommonConstant.LONG_TEXT)
    private String description;

    @NotNull(message = MessageConstant.PRICE_NOT_NULL)
    @Min(value = 0, message = MessageConstant.PRICE_MIN)
    @Column(columnDefinition = CommonConstant.INT)
    private int price;

    @NotNull(message = MessageConstant.CREATE_DATE_NOT_NULL)
    @PastOrPresent(message = MessageConstant.CREATE_DATE_PAST_OR_PRESENT)
    @Column
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }


    @ManyToMany
    @JoinTable(
            name = EntityConstant.DRINK_TOPPING_TABLE,
            joinColumns = @JoinColumn(name = EntityConstant.DRINK_ID),
            inverseJoinColumns = @JoinColumn(name = EntityConstant.TOPPING_ID))
    private List<ToppingEntity> toppings;


}
