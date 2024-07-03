package com.ManageDrink.services.implement;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.repository.ToppingRepository;
import com.ManageDrink.services.IDrinkService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DrinkService implements IDrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private ToppingRepository toppingRepository;
    @Override
    @Transactional
    public DrinkDTO createDrink(DrinkDTO drinkDTO) {
        DrinkEntity drinkEntity = this.convertDTOTOEntity(drinkDTO);
        List<ToppingEntity> toppingEntities = toppingRepository.findAllById(drinkDTO.getListIds());
        drinkEntity.setToppings(toppingEntities);
        drinkRepository.save(drinkEntity);
        return drinkDTO;
    }
    public DrinkDTO updateDrink(DrinkDTO drinkDTO){
        DrinkEntity drinkEntity = drinkRepository.findById(drinkDTO.getId()).get();
        drinkEntity.setNameDrink(drinkDTO.getNameDrink());
        drinkEntity.setId(drinkDTO.getId());
        drinkEntity.setDescription(drinkDTO.getDescription());
        drinkEntity.setToppings(toppingRepository.findAllById(drinkDTO.getListIds()));
        drinkRepository.save(drinkEntity);
        return drinkDTO;
    }




    private DrinkEntity convertDTOTOEntity(DrinkDTO drinkDTO){
        DrinkEntity drinkEntity = DrinkEntity.builder()
                .id(drinkDTO.getId())
                .nameDrink(drinkDTO.getNameDrink())
                .description(drinkDTO.getDescription())
                .createDate(drinkDTO.getCreateDate())
                .price(drinkDTO.getPrice())
                .build();
        return drinkEntity;
    }

}
