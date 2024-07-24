package com.managedrink.until.mapper;

import com.managedrink.dto.UserDTO;
import com.managedrink.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public static UserDTO ConvertEntityToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .permissions(userEntity.getPermissions())
                .roles(userEntity.getRoles())
                .build();
    }

    public static UserEntity ConvertDTOToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .permissions(userDTO.getPermissions())
                .roles(userDTO.getRoles())
                .build();
    }
}
