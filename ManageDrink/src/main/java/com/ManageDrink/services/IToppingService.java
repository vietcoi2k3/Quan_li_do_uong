package com.ManageDrink.services;

import com.ManageDrink.entity.ToppingEntity;

import java.util.List;
import java.util.Optional;

public interface IToppingService {

    boolean deleteTopping(Long idTopping);

    List<ToppingEntity> getToppingsByIds(List<Long> ids);

    void saveTopping(ToppingEntity toppingEntity);

}
