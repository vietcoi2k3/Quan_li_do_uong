package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
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

    @NotEmpty(message = "name drink cannot be empty")
    @Size(max = 50, message = "name drink must be less than 50 characters")
    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String nameDrink;

    @Size(max = 1000, message = "description must be less than 1000 characters")
    @Column(columnDefinition = CommonConstant.LONG_TEXT)
    private String description;

    @Min(value = 0, message = "price must be equal or greater than zero")
    @Column(columnDefinition = CommonConstant.INT)
    private int price;

    @NotNull(message = "create date cannot be null")
    @PastOrPresent(message = "create date must be a past or present date")
    @Column
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    @ManyToMany
    @JoinTable(
            name = "drink_topping",
            joinColumns = @JoinColumn(name = "drink_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<ToppingEntity> toppings;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }

    private int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_THOUSAND) * CommonConstant.ONE_THOUSAND;
    }


}
