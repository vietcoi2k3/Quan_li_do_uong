package com.ManageDrink.services;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.ToppingEntity;

import java.util.List;
import java.util.Optional;

public interface IToppingService {

    boolean deleteTopping(Long idTopping);

    List<ToppingDTO> getListToppingByIdDrink(Long id);

    ToppingDTO saveTopping(ToppingDTO toppingDTO);

    ToppingDTO updateTopping(ToppingDTO toppingDTO);



}
