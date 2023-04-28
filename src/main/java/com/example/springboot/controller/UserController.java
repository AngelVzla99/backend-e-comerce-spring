package com.example.springboot.controller;

import com.example.springboot.converter.UserConverter;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.pojo.UserRequestAddRole;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    // ===============
    //    get EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable Long id) {
        UserConverter mapper = new UserConverter();
        Optional<UserEntity> user = userService.getById(id);
        if (user.isPresent()) return mapper.toDTO(user.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user is not in the database");
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create-user")
    @ResponseBody
    public UserDTO createUserAdmin(@RequestBody UserDTO user) {
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
        return mapper.toDTO(userService.save(userEntity));
    }

    @PostMapping("/create-customer")
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDTO user) {
        // Search each role in the database
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity role = roleService.findByRoleNameOrCreate("customer");
        roles.add(role);

        // convert DTO to Entity
        UserConverter mapper = new UserConverter();
        UserEntity userEntity = mapper.toEntity(user, roles);
        return mapper.toDTO(userService.save(userEntity));
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{userId}/add-roles")
    @ResponseBody
    public void addRole(@PathVariable Long userId, @RequestBody UserRequestAddRole request) {
        UserEntity user = userService
                .getById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // add each role the user list
        Set<RoleEntity> userRoles = user.getRoles();
        for (Long roleId : request.getRoleIds()) {
            RoleEntity role = roleService
                    .getById(roleId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role " + roleId + " not found"));
            userRoles.add(role);
        }

        // update the database
        user.setRoles(userRoles);
        userService.save(user);
    }

    // ================
    //   delete EPs  //
    // ================

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "ok";
    }
}
