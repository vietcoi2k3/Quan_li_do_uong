package com.managedrink.repository;

import com.managedrink.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class: UserRepository
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
