package com.ManageDrink.until.mapper;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DrinkMapper {

    public static DrinkEntity convertDTOTOEntity(DrinkDTO drinkDTO) {
        return DrinkEntity.builder()
                .id(drinkDTO.getId())
                .nameDrink(drinkDTO.getNameDrink())
                .description(drinkDTO.getDescription())
                .createDate(drinkDTO.getCreateDate())
                .price(drinkDTO.getPrice())
                .build();
    }


    public static DrinkDTO convertEntityTODTO(DrinkEntity drinkEntity) {
        List<String> toppingNames = drinkEntity.getToppings().stream()
                .map(ToppingEntity::getName)
                .collect(Collectors.toList());

        return DrinkDTO.builder()
                .id(drinkEntity.getId())
                .nameDrink(drinkEntity.getNameDrink())
                .description(drinkEntity.getDescription())
                .createDate(drinkEntity.getCreateDate())
                .price(drinkEntity.getPrice())
                .toppingNames(toppingNames)
                .build();
    }

}
