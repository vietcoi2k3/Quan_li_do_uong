package com.ManageDrink.services;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.entity.ToppingEntity;

import java.util.List;
import java.util.Optional;

public interface IToppingService {

    boolean deleteTopping(Long idTopping);

    List<ToppingEntity> getToppingsByIds(List<Long> ids);

    List<ToppingDTO> getListTopping(Long id);

    ToppingDTO saveTopping(ToppingDTO toppingDTO);

    ToppingDTO updateTopping(ToppingDTO toppingDTO);



}
