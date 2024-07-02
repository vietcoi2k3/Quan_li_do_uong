package com.ManageDrink.controller;

import com.ManageDrink.services.implement.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    @Autowired
    private ToppingService toppingService;

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteDrink(@PathVariable("id") Long id) {
        if (toppingService.deleteTopping(id)) {
            return ResponseEntity.ok("Topping with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topping not found with id: " + id);
        }
    }
}
