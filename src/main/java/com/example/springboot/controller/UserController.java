package com.example.springboot.controller;

import com.example.springboot.model.UserEntity;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public UserEntity getUser(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping("/create")
    @ResponseBody
    public UserEntity createUser(@RequestBody UserEntity user){
        System.out.println(  );
        System.out.println( user.toString() );
        System.out.println(  );
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "ok";
    }


}
