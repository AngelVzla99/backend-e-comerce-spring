package com.example.springboot.controller;

import com.example.springboot.converter.PermissionConverter;
import com.example.springboot.converter.RoleConverter;
import com.example.springboot.dto.PermissionDTO;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.model.PermissionEntity;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.service.PermissionService;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/{id}")
    @ResponseBody
    public RoleDTO getUser(@PathVariable Long id){
        RoleConverter mapper = new RoleConverter();
        RoleEntity roleEntity = roleService.getById(id);
        return mapper.toDTO(roleEntity);
    }

    @PostMapping("/create")
    @ResponseBody
    public RoleEntity createUser(@RequestBody RoleDTO roleDTO) {
        RoleConverter roleConverter = new RoleConverter();
        PermissionConverter permissionConverter = new PermissionConverter();
        // users for this role
        Set<UserEntity> users = new HashSet<>();

        // permissions for this role (create all the permissions)
        Set<PermissionEntity> permissions = new HashSet<>();
        for(PermissionDTO permissionDTO : roleDTO.getPermissions()){
            PermissionEntity permissionEntity = permissionConverter.toEntity(permissionDTO);
            permissionEntity = permissionService.save( permissionEntity );
            permissions.add( permissionEntity );
        }

        RoleEntity roleEntity = roleConverter.toEntity(roleDTO,users,permissions);
        return roleService.save( roleEntity );
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id){
        roleService.delete(id);
        return "ok";
    }

}
