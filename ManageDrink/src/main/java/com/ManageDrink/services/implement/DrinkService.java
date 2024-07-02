package com.ManageDrink.services.implement;

import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.repository.DrinkRepository;
import com.ManageDrink.services.IDrinkService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class DrinkService implements IDrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private ToppingService toppingService;

    @Override
    @Transactional
    public ResponseEntity<?> createDrink(DrinkEntity drinkEntity) {
        try {
            checkIfExistsByName(drinkEntity.getNameDrink());

             drinkRepository.save(drinkEntity);
             saveOrUpdateToppings(drinkEntity); // Lưu hoặc cập nhật các topping

            return ResponseEntity.ok(drinkEntity);

        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A drink with this name already exists.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the drink.");
        }
    }

    @Override
    public ResponseEntity<?> updateDrink(DrinkEntity drinkEntity) {
        try {

            if (drinkRepository.findById(drinkEntity.getId()).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drink not found with id: " + drinkEntity.getId());
            }

//           checkIfExistsByName(drinkEntity.getNameDrink());
            drinkRepository.save(drinkEntity);
            saveOrUpdateToppings(drinkEntity); // Lưu hoặc cập nhật các topping

            return ResponseEntity.ok(drinkEntity);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A drink with this name already exists.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the drink.");
        }
    }


    @Override
    public Page<DrinkEntity> getDrinks(Pageable pageable) {
        return drinkRepository.findAll(pageable);
    }


    @Transactional
    public boolean deleteDrink(Long id) {
        try {
            Optional<DrinkEntity> drinkOptional = drinkRepository.findById(id);
            if (drinkOptional.isPresent()) {
                drinkRepository.deleteById(id);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log lỗi
            return false; // Xóa không thành công
        }
        return false;
    }

    private void checkIfExistsByName(String nameDrink) {
        Optional<DrinkEntity> existingDrink = drinkRepository.findByNameDrink(nameDrink);
        if (existingDrink.isPresent()) {
            throw new IllegalStateException("A drink with this name already exists.");
        }
    }


    private void saveOrUpdateToppings(DrinkEntity drinkEntity) {
        if (!(drinkEntity.getToppingEntities() == null)) {
            List<ToppingEntity> listTopping = drinkEntity.getToppingEntities();
            for (ToppingEntity toppingEntity : listTopping) {
                toppingEntity.setDrinkEntityID(drinkEntity.getId());
                toppingService.saveTopping(toppingEntity);
            }
        }

    }



}
