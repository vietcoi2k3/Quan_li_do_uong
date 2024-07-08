package com.ManageDrink.services.implement;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.exception.NotNullException;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DrinkService implements IDrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private ToppingRepository toppingRepository;


    @Override
    @Transactional
    public DrinkDTO createDrink(DrinkDTO drinkDTO) {
            if (drinkDTO==null){
                throw new NotNullException(MessageConstant.DRINK_NOT_NULL);
            }
            DrinkEntity drinkEntity = DrinkMapper.convertDTOToEntity(drinkDTO);

            drinkEntity.setCreateDate(LocalDate.now());//lấy thời điểm hiện tại làm ngày tạo

            List<ToppingEntity> toppingEntities = toppingRepository.findAllById(drinkDTO.getListIds());
            drinkEntity.setToppings(toppingEntities);
            drinkEntity = drinkRepository.save(drinkEntity);

            DrinkDTO result = DrinkMapper.convertEntityTODTO(drinkEntity);
            result.setListIds(drinkDTO.getListIds());
            return result;
        }

    @Override
    public Page<DrinkDTO> getDrinks(Pageable pageable) {
        Page<DrinkEntity> drinkEntities = drinkRepository.findAll(pageable);
        return drinkEntities.map(DrinkMapper::convertEntityTODTO);
    }


    @Override
    @Transactional
    public DrinkDTO updateDrink(DrinkDTO drinkDTO) {
            if (drinkDTO == null){
                throw new NotNullException(MessageConstant.DRINK_NOT_NULL);
            }
            DrinkEntity drinkEntity = drinkRepository.findById(drinkDTO.getId()).get();

            drinkEntity.setNameDrink(drinkDTO.getNameDrink());
            drinkEntity.setDescription(drinkDTO.getDescription());

            drinkEntity.setToppings(toppingRepository.findAllById(drinkDTO.getListIds()));
            drinkRepository.save(drinkEntity);

            return drinkDTO;
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
