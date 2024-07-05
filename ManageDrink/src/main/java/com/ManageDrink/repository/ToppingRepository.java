package com.ManageDrink.repository;

import com.ManageDrink.entity.ToppingEntity;
import com.ManageDrink.until.constant.EntityConstant;
import com.ManageDrink.until.constant.ProcedureNameConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends JpaRepository<ToppingEntity,Long> {

    @Procedure(value = ProcedureNameConstant.GET_TOPPING_BY_DRINK)
    List<ToppingEntity> getToppingEntitiesByDrinkId(@Param(EntityConstant.DRINK_ENTITY_ID) Long id);
}
