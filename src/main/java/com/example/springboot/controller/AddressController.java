package com.example.springboot.controller;

import com.example.springboot.converter.AddressConverter;
import com.example.springboot.dto.AddressDTO;
import com.example.springboot.model.AddressEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.service.AddressService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressConverter addressConverter;

    // ===============
    //    get EPs   //
    // ===============

    @GetMapping
    @ResponseBody
    public List<AddressDTO> getCart(Principal principal) {
        // only a user can modify his own cart
        UserEntity user = userService.findByEmailToken(principal.getName());
        // convert entities to DTOs
        return addressConverter.toDtoList( user.getAddresses() );
    }

    // ===============
    //   post EPs   //
    // ===============

    @PostMapping
    @ResponseBody
    public AddressDTO save(Principal principal, @RequestBody AddressDTO addressDTO) {
        // only a user can modify his own cart
        UserEntity user = userService.findByEmailToken(principal.getName());
        // convert the new entity to DTOs and save in the db
        addressDTO.setUserId(user.getId());
        AddressEntity entity = addressConverter.toEntity(addressDTO);
        addressService.save(entity);
        return addressConverter.toDto(entity);
    }

    // =================
    //   delete EPs   //
    // =================

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(Principal principal, @PathVariable Long id) {
        // only a user can modify his own cart
        UserEntity user = userService.findByEmailToken(principal.getName());
        // find user address in the database
        AddressEntity address = addressService
                .findByUserAndId(user,id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This address don't belong to the user of the token, or not exist"));
        // delete from the db
        addressService.delete(id);
    }
}
