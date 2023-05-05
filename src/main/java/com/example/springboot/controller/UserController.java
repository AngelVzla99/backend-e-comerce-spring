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

    // ===============
    //    get EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create-user")
    @ResponseBody
    public UserDTO createUserAdmin(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    @PostMapping("/create-customer")
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.saveCustomer(user);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{userId}/add-roles")
    @ResponseBody
    public UserDTO addRole(@PathVariable Long userId, @RequestBody UserRequestAddRole request) {
        return userService.addRole( userId, request.getRoleIds() );
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
