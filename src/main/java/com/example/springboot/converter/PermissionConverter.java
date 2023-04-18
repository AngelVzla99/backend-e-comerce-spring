package com.example.springboot.converter;

import com.example.springboot.dto.PermissionDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.PermissionEntity;
import com.example.springboot.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PermissionConverter {
    public PermissionDTO toDTO(PermissionEntity permissionEntity) {
        return new PermissionDTO(
            permissionEntity.getId(),
            permissionEntity.getSectionName(),
            permissionEntity.getCreate(),
            permissionEntity.getUpdate(),
            permissionEntity.getDelete(),
            permissionEntity.getRead()
        );
    }

    public PermissionEntity toEntity(PermissionDTO permission) {
        return new PermissionEntity(
                permission.getId(),
                permission.getSectionName(),
                permission.getCreate(),
                permission.getUpdate(),
                permission.getDelete(),
                permission.getRead()
        );
    }
}
