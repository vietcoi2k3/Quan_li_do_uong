package com.managedrink.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

      @Column(columnDefinition = "id")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(columnDefinition = "username")
      private String userName;

      @Column(columnDefinition = "password")
      private String password;

      @Column(columnDefinition = "role")
      private String role;
}
