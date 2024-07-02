package com.ManageDrink.services;

import com.ManageDrink.entity.DrinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IDrinkService {

    // Add CRUD operations here

    ResponseEntity<?> createDrink(DrinkEntity drinkEntity);
    Page<DrinkEntity> getDrinks(Pageable pageable);
    ResponseEntity<?> updateDrink(DrinkEntity drinkEntity);
    boolean deleteDrink(Long id);


}
