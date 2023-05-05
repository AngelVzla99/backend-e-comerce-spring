package com.example.springboot.controller;

import com.example.springboot.converter.CartItemConverter;
import com.example.springboot.dto.CartItemDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.CartItemEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.pojo.UserCartUpdate;
import com.example.springboot.service.CartItemService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users-cart")
public class CartItemController {
    @Autowired
    UserService userService;
    @Autowired
    CartItemConverter cartItemConverter;
    @Autowired
    CartItemService cartItemService;

    // ===============
    //    get EPs   //
    // ===============

    @GetMapping()
    @ResponseBody
    public List<CartItemDTO> getCart(Principal principal) {
        return userService.getCartItems(principal.getName());
    }

    // ===============
    //   post EPs   //
    // ===============

    @PutMapping("/update")
    @ResponseBody
    public void updateUserCart(
            Principal principal,
            @RequestBody UserCartUpdate request
    ) {
        userService.updateUserCart( principal.getName(), request.getProducts() );
    }
}
