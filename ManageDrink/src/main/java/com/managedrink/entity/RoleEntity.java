package com.managedrink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Class: RoleEntity
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
