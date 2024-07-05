package com.ManageDrink.services;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IDrinkService {

    DrinkDTO createDrink(DrinkDTO drinkDTO);

    Page<DrinkDTO> getDrinks(Pageable pageable);

    DrinkDTO updateDrink(DrinkDTO drinkDTO);

    ResponseEntity<?> deleteDrink(Long id);

}
