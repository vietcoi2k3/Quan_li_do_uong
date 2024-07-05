package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.ManageDrink.until.constant.ValidateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @NotEmpty(message = ValidateConstant.NAME_DRINK_NOT_EMPTY)
    @NotNull(message =ValidateConstant.NAME_DRINK_NOT_NULL)
    @Size(max = 50, message = ValidateConstant.NAME_DRINK_MAX_SIZE)
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String nameDrink;

    @Size(max = 1000, message = ValidateConstant.DESCRIPTION_DRINK_MAX_SIZE)
    @Column(columnDefinition = CommonConstant.LONG_TEXT)
    private String description;

    @Min(value = 0, message = ValidateConstant.PRICE_DRINK_MUST_GREATER_ZERO)
    @Column(columnDefinition = CommonConstant.INT)
    private int price;

    @Column
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    @ManyToMany
    @JoinTable(
            name = "drink_topping",
            joinColumns = @JoinColumn(name = EntityConstant.DRINK_ENTITY_ID),
            inverseJoinColumns = @JoinColumn(name = EntityConstant.TOPPING_ID))
    private List<ToppingEntity> toppings;
}
