package com.example.springboot.controller;

import com.example.springboot.converter.UserConverter;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.PermissionEntity;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.service.PermissionService;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @PreAuthorize("hasAnyRole('admin','customer')")
    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable Long id){
        UserConverter mapper = new UserConverter();
        Optional<UserEntity> user =  userService.getById(id);
        if(user.isPresent()) return mapper.toDTO(user.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This user is not in the database");
    }

    @PostMapping("/create")
    @ResponseBody
    public UserEntity createUser(@RequestBody UserDTO user) {
        System.out.println();
        System.out.println(user.toString());
        System.out.println();

        // Search each role in the database
        Set<RoleEntity> roles = new HashSet<>();
        for (Long id : user.getRoles()) {
            Optional<RoleEntity> roleTemp = roleService.getById(id);
            if (roleTemp.isPresent()) roles.add(roleTemp.get());
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // convert DTO to Entity
        UserConverter mapper = new UserConverter();
        UserEntity userEntity = mapper.toEntity(user, roles);
        return userService.save(userEntity);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "ok";
    }


}
