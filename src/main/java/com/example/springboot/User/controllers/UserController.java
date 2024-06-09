package com.example.springboot.User.controllers;

import com.example.springboot.User.dto.UserRequestAddRole;
import com.example.springboot.User.dto.UserDTO;
import com.example.springboot.User.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public UserDTO createUserAdmin( @Valid @RequestBody UserDTO user) {
        if( userService.isEmailInDataBase(user.getEmail()) )
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The email is already in the database");
        return userService.save(user);
    }

    @PostMapping("/create-customer")
    @ResponseBody
    public UserDTO createUser( @Valid @RequestBody UserDTO user) {
        if( userService.isEmailInDataBase(user.getEmail()) )
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The email is already in the database");
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
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
