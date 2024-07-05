package com.ManageDrink.until.mapper;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.exception.NotNullException;
import com.ManageDrink.until.constant.MessageConstant;

import java.util.List;
import java.util.stream.Collectors;

public class DrinkMapper {

    public static DrinkEntity convertDTOToEntity(DrinkDTO drinkDTO) {
        if (drinkDTO == null){
            throw new NotNullException(MessageConstant.DRINK_NOT_NULL);
        }
        return DrinkEntity.builder()
                .id(drinkDTO.getId())
                .nameDrink(drinkDTO.getNameDrink())
                .description(drinkDTO.getDescription())
                .price(drinkDTO.getPrice())
                .build();
    }


    public static DrinkDTO convertEntityTODTO(DrinkEntity drinkEntity) {
        if (drinkEntity==null){
            throw new NotNullException(MessageConstant.DRINK_NOT_NULL);
        }
        return DrinkDTO.builder()
                .id(drinkEntity.getId())
                .nameDrink(drinkEntity.getNameDrink())
                .description(drinkEntity.getDescription())
                .price(drinkEntity.getPrice())
                .build();
    }

}
