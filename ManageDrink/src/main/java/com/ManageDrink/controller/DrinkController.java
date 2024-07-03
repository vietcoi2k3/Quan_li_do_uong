package com.ManageDrink.controller;

import com.ManageDrink.dto.DrinkDTO;
import com.ManageDrink.entity.DrinkEntity;
import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.services.implement.DrinkService;
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


    @PostMapping("/create")
    public ResponseEntity<?> createDrink(@Valid @RequestBody DrinkDTO drinkDTO) {
        return new ResponseEntity<>(drinkService.createDrink(drinkDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDrink(@RequestBody DrinkDTO drinkDTO) {
        return ResponseEntity.ok(drinkService.updateDrink(drinkDTO));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllDrinks(
            @RequestParam(defaultValue = CommonConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = CommonConstant.DEFAULT_SIZE) int size) {


        Pageable pageable = PageRequest.of(page, size);
        Page<DrinkDTO> drinkDTOS = drinkService.getDrinks(pageable);


        return new ResponseEntity<>(drinkDTOS.getContent(), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDrink(@PathVariable Long id) {
        return ResponseEntity.ok(drinkService.deleteDrink(id));
    }



}
