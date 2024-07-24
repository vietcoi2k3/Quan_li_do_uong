package com.managedrink.repository;

import com.managedrink.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class: RoleRepository
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
