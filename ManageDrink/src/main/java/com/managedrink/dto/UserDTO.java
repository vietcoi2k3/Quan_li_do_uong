package com.managedrink.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.managedrink.entity.PermissionEnum;
import com.managedrink.entity.RoleEntity;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotEmpty(message = "userName must not be empty")
    private String username;
    @NotEmpty(message = "password must not be empty")
    private String password;

    private Set<PermissionEnum> permissions;
    private Set<RoleEntity> roles;
}
