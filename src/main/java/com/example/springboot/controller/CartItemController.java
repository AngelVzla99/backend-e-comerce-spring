package com.example.springboot.controller;

import com.example.springboot.converter.CartItemConverter;
import com.example.springboot.dto.CartItemDTO;
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
        // only a user can modify his own cart
        String email = principal.getName();
        UserEntity user = userService
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The email of your token is not in the database"));
        // convert entities to DTOs
        return user.getCartItems().stream().map(cartItemConverter::toDTO).collect(Collectors.toList());
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
        // only a user can modify his own cart
        String email = principal.getName();
        UserEntity user = userService
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The email of your token is not in the database"));
        // delete old products in the cart
        user.getCartItems().stream().forEach(item -> cartItemService.delete(item.getId()));
        // search the list of cart items
        request.getProducts().stream().forEach(product -> product.setUserId(user.getId()));
        List<CartItemEntity> newCartList = cartItemConverter
                .toEntityList(request.getProducts());
        // update the database
        newCartList.stream().forEach(item -> cartItemService.save(item));
    }
}
