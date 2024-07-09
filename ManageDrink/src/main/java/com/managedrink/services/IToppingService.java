package com.managedrink.services;

import com.managedrink.dto.ToppingDTO;
import com.managedrink.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IToppingService {

    ResponseEntity<?> deleteTopping(Long idTopping);

    List<ToppingDTO> getListToppingByIdDrink(Long id) throws NotFoundException;

    ToppingDTO saveTopping(ToppingDTO toppingDTO);

    ToppingDTO updateTopping(ToppingDTO toppingDTO) throws NotFoundException;

    List<ToppingDTO> getAllTopping();

}
