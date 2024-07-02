package com.ManageDrink.controller;

import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.services.implement.DrinkService;
import com.ManageDrink.services.implement.ToppingService;
import com.ManageDrink.until.constant.CommonConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ToppingService toppingService;

    @PostMapping("/create")
    public ResponseEntity<?> createDrink(@Valid @RequestBody DrinkEntity drinkEntity) {
       processToppings(drinkEntity);
        ResponseEntity<?> createdDrink = drinkService.createDrink(drinkEntity);
        return new ResponseEntity<>(createdDrink, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<DrinkEntity>> getAllDrinks
            (@RequestParam(defaultValue = CommonConstant.DEFAULT_PAGE) int page,
             @RequestParam(defaultValue = CommonConstant.DEFAULT_SIZE) int size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page number must not be less than zero");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<DrinkEntity> drinkEntities = drinkService.getDrinks(pageable);
        return new ResponseEntity<>(drinkEntities.getContent(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDrink(@Valid @RequestBody DrinkEntity drinkEntity) {


        processToppings(drinkEntity);
        ResponseEntity<?> updatedDrink = drinkService.updateDrink(drinkEntity);

        return new ResponseEntity<>(updatedDrink, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteDrink(@PathVariable("id") Long id) {
        if (drinkService.deleteDrink(id)) {
            return ResponseEntity.ok("Drink with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drink not found with id: " + id);
        }
    }


    private void processToppings(DrinkEntity drinkEntity) {
        // Kiểm tra nếu DrinkEntity không chứa ToppingEntities hoặc danh sách rỗng
        if (drinkEntity.getToppingEntities() == null || drinkEntity.getToppingEntities().isEmpty()) {
            drinkEntity.setToppingEntities(null);
            return;
        }

        // Lấy danh sách các ID của ToppingEntity từ DrinkEntity
        List<Long> toppingIds = drinkEntity.getToppingEntities().stream()
                .map(ToppingEntity::getId)
                .collect(Collectors.toList());

        // Kiểm tra nếu toppingIds trống
        if (toppingIds.isEmpty()) {
            drinkEntity.setToppingEntities(null);
            return;
        }

        // Tìm kiếm các ToppingEntity tương ứng với các ID này
        List<ToppingEntity> toppings = toppingService.getToppingsByIds(toppingIds);

        // Kiểm tra nếu không tìm thấy topping nào
        if (toppings == null || toppings.isEmpty()) {
            drinkEntity.setToppingEntities(null);
            return;
        }

        // Gán danh sách các ToppingEntity tìm thấy cho DrinkEntity
        drinkEntity.setToppingEntities(toppings);
    }




}
