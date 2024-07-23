package com.managedrink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Setter(AccessLevel.NONE)
      private Long id;

      @Column(nullable = false, unique = true)
      private String username;

      @Column(nullable = false)
      private String password;

      @ManyToMany(fetch = FetchType.EAGER)
      @JoinTable(
              name = "user_roles",
              joinColumns = @JoinColumn(name = "user_id"),
              inverseJoinColumns = @JoinColumn(name = "role_id")
      )
      private Set<RoleEntity> roles;
}
