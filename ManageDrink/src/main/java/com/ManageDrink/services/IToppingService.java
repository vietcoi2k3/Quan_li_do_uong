package com.ManageDrink.services;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IToppingService {

    boolean deleteTopping(Long idTopping);

    List<ToppingDTO> getListToppingByIdDrink(Long id) throws NotFoundException;

    ToppingDTO saveTopping(ToppingDTO toppingDTO);

    ToppingDTO updateTopping(ToppingDTO toppingDTO) throws NotFoundException;



}
