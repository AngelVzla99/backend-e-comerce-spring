package com.example.springboot.converter;

import com.example.springboot.dto.RoleDTO;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RoleConverter {
    public RoleDTO toDTO(RoleEntity roleEntity) {
        return new RoleDTO(
                roleEntity.getId(),
                roleEntity.getRoleName()
        );
    }

    public RoleEntity toEntity(RoleDTO roleDTO, Set<UserEntity> users) {
        return new RoleEntity(
                roleDTO.getId(),
                roleDTO.getRoleName(),
                users
        );
    }
}
