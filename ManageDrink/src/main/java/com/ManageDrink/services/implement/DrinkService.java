package com.ManageDrink.services.implement;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.repository.ToppingRepository;
import com.ManageDrink.services.IDrinkService;
import com.ManageDrink.until.constant.MessageConstant;
import com.ManageDrink.until.mapper.DrinkMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ManageDrink.until.mapper.DrinkMapper.convertDTOTOEntity;

@Service
public class DrinkService implements IDrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private ToppingRepository toppingRepository;


    @Override
    @Transactional
    public ResponseEntity<?> createDrink(DrinkDTO drinkDTO) {
        try {

            DrinkEntity drinkEntity = convertDTOTOEntity(drinkDTO);
            List<ToppingEntity> toppingEntities = toppingRepository.findAllById(drinkDTO.getListIds());
            drinkEntity.setToppings(toppingEntities);
            drinkRepository.save(drinkEntity);
            return ResponseEntity.ok(drinkEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageConstant.ERROR_CREATING_DRINK);
        }
    }

    @Override
    public Page<DrinkDTO> getDrinks(Pageable pageable) {
        Page<DrinkEntity> drinkEntities = drinkRepository.findAll(pageable);
        return drinkEntities.map(DrinkMapper::convertEntityTODTO);
    }


    @Override
    @Transactional
    public ResponseEntity<?> updateDrink(DrinkDTO drinkDTO) {

        try {
            DrinkEntity drinkEntity = drinkRepository.findById(drinkDTO.getId()).get();
            drinkEntity.setNameDrink(drinkDTO.getNameDrink());
            drinkEntity.setId(drinkDTO.getId());
            drinkEntity.setDescription(drinkDTO.getDescription());
            drinkEntity.setToppings(toppingRepository.findAllById(drinkDTO.getListIds()));
            drinkRepository.save(drinkEntity);
            return ResponseEntity.ok(drinkEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageConstant.ERROR_UPDATING_DRINK);
        }


    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteDrink(Long id) {
        try {
            Optional<DrinkEntity> drinkEntityOptional = drinkRepository.findById(id);

            if (!drinkEntityOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstant.DRINK_NOT_FOUND);
            }

            drinkRepository.deleteById(id);
            return ResponseEntity.ok(MessageConstant.DRINK_DELETED_SUCCESSFULLY);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstant.ERROR_DELETING_DRINK);
        }
    }


}
