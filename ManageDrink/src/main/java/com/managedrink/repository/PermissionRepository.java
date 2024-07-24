package com.managedrink.repository;

import com.managedrink.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class: PermissionRepository
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {
}
