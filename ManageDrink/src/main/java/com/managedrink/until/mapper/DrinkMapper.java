package com.managedrink.until.mapper;

import com.managedrink.dto.DrinkDTO;
import com.managedrink.entity.DrinkEntity;
import com.managedrink.exception.NotNullException;
import com.managedrink.until.constants.MessageConstant;

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
