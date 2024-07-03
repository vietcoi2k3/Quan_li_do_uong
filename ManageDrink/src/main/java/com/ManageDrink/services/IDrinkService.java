package com.ManageDrink.services;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IDrinkService {

    // Add CRUD operations here

    DrinkDTO createDrink(DrinkDTO drinkDTO);
}
