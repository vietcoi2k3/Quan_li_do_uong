package com.ManageDrink.entity;

import com.ManageDrink.until.constant.CommonConstant;
import com.ManageDrink.until.constant.EntityConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
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

    @Column(columnDefinition = CommonConstant.VARCHAR_50)
    private String nameDrink;

    @Column(columnDefinition = CommonConstant.LONG_TEXT)
    private String description;

    @Column(columnDefinition = CommonConstant.INT)
    private int price;

    @Column
    @JsonFormat(pattern = CommonConstant.DATE_FORMAT)
    private LocalDate createDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = EntityConstant.DRINK_ENTITY)
    @JsonManagedReference(value = "drink-topping")
    private List<ToppingEntity> toppingEntities;

    public void setPrice(int price) {
        this.price = roundPrice(price);
    }

    private int roundPrice(int price) {
        if (price<CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_THOUSAND) * CommonConstant.ONE_THOUSAND;
    }
}
