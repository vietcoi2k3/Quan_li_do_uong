package com.ManageDrink.until.mapper;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.ToppingEntity;

public class ToppingMapper {

    public static ToppingEntity convertDTOToEntity(ToppingDTO toppingDTO){
        if (toppingDTO==null){
            return null;
        }
        ToppingEntity toppingEntity = ToppingEntity.builder()
                .id(toppingDTO.getId())
                .name(toppingDTO.getName())
                .price(toppingDTO.getPrice())
                .build();
        return toppingEntity;
    }

    public static ToppingDTO convertEntityTODTO(ToppingEntity toppingEntity){
        if (toppingEntity == null){
            return null;
        }
        ToppingDTO toppingDTO = ToppingDTO.builder()
                .id(toppingEntity.getId())
                .price(toppingEntity.getPrice())
                .name(toppingEntity.getName())
                .build();
        return toppingDTO;
    }
}
