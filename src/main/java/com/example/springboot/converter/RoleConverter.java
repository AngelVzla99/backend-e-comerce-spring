package com.example.springboot.converter;

import com.example.springboot.dto.PermissionDTO;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.PermissionEntity;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RoleConverter {
    public RoleDTO toDTO(RoleEntity roleEntity) {
        PermissionConverter permissionConverter = new PermissionConverter();
        List<PermissionDTO> permissions = new ArrayList<>();
        for (PermissionEntity permission : roleEntity.getPermissions())
            permissions.add( permissionConverter.toDTO(permission) );
        return new RoleDTO(
                roleEntity.getId(),
                roleEntity.getRoleName(),
                permissions
        );
    }

    public RoleEntity toEntity(RoleDTO roleDTO, Set<UserEntity> users, Set<PermissionEntity> permissions) {
        return new RoleEntity(
                roleDTO.getId(),
                roleDTO.getRoleName(),
                users,
                permissions
        );
    }
}
