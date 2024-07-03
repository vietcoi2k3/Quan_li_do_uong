package com.ManageDrink.repository;

import com.ManageDrink.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity,Long> {

    Optional<DrinkEntity> findByNameDrink(String name);

}
