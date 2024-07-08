package com.ManageDrink.controller;

import com.ManageDrink.dto.ToppingDTO;
import com.ManageDrink.exception.NotFoundException;
import com.ManageDrink.services.implement.ToppingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    @Autowired
    private ToppingService toppingService;

    @PostMapping("/create")
    public ResponseEntity<?> addTopping(@RequestBody @Valid ToppingDTO toppingDTO){
        return new ResponseEntity<>(toppingService.saveTopping(toppingDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTopping(@RequestBody @Valid ToppingDTO toppingDTO)  {
        return new ResponseEntity<>(toppingService.updateTopping(toppingDTO),HttpStatus.OK);
    }

    @GetMapping("/get-list-topping")
    public ResponseEntity<?> getListTopping(@RequestParam Long drinkID)  {
        return new ResponseEntity<>(toppingService.getListToppingByIdDrink(drinkID),HttpStatus.OK);
    }

    @GetMapping("/get-all-topping")
    public ResponseEntity<?> getAllTopping(){
        return new ResponseEntity<>(toppingService.getAllTopping(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteDrink(@PathVariable("id") Long id) {
        if (toppingService.deleteTopping(id)) {
            return ResponseEntity.ok("Topping with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topping not found with id: " + id);
        }
    }
}
