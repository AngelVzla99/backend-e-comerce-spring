package com.example.springboot.Auth.controllers;

import com.example.springboot.Auth.LoginRequest;
import com.example.springboot.User.dto.UserDTO;
import com.example.springboot.User.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    // ===============
    //   post EPs   //
    // ===============

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest dto) {
        return userService.getUserToken(dto.getEmail(), dto.getPassword()



        );
    }
}
