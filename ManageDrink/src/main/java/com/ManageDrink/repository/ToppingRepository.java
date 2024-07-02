package com.ManageDrink.repository;

import com.ManageDrink.entity.ToppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<ToppingEntity,Long> {


}
