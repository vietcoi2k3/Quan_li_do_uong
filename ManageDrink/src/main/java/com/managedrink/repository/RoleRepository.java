package com.managedrink.repository;

import com.managedrink.entity.RoleEntity;
import com.managedrink.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Class: RoleRepository
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(String name);



}
