package com.example.springboot.service;

import com.example.springboot.converter.AddressConverter;
import com.example.springboot.converter.CartItemConverter;
import com.example.springboot.converter.RoleConverter;
import com.example.springboot.converter.UserConverter;
import com.example.springboot.dto.AddressDTO;
import com.example.springboot.dto.CartItemDTO;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.RoleEntityRepository;
import com.example.springboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    RoleEntityRepository roleEntityRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    RoleConverter roleConverter;
    @Autowired
    AddressConverter addressConverter;
    @Autowired
    CartItemConverter cartItemConverter;
    @Autowired
    CartItemService cartItemService;

    public UserDTO findById(Long id) {
        UserConverter mapper = new UserConverter();
        Optional<UserEntity> user = userEntityRepository.findById(id);
        if (user.isPresent()) return mapper.toDTO(user.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user is not in the database");
    }

    public UserDTO save(UserDTO user) {
        // Search each role in the database
        Set<RoleEntity> roles = new HashSet<>();
        for (Long id : user.getRoles()) {
            Optional<RoleEntity> roleTemp = roleEntityRepository.findById(id);
            if (roleTemp.isPresent()) roles.add(roleTemp.get());
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // convert DTO to Entity
        UserEntity userEntity = userConverter.toEntity(user, roles);
        return userConverter.toDTO(userEntityRepository.save(userEntity));
    }

    public UserDTO findByEmail(String email){
        Optional<UserEntity> user = userEntityRepository.findOneByEmail(email);
        if (user.isPresent()) return userConverter.toDTO(user.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user is not in the database");
    }

    public UserDTO addRole(Long userId, List<Long> roleIds){
        UserEntity userEntity = userEntityRepository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product with id "+ userId +" was not found"));

        // add each role the user list
        Set<RoleEntity> userRoles = userEntity.getRoles();
        for (Long roleId : roleIds) {
            RoleEntity role = roleEntityRepository
                    .findById(roleId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role " + roleId + " not found"));
            userRoles.add(role);
        }

        // update the database
        userEntity.setRoles(userRoles);
        return userConverter.toDTO( userEntityRepository.save(userEntity) );
    }

    public UserDTO saveCustomer(UserDTO user){
        // Search each role in the database
        RoleEntity role = roleService.findByRoleNameOrCreate("customer");
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add( role );

        // convert DTO to Entity
        UserEntity userEntity = userConverter.toEntity(user, roleEntities);
        return userConverter.toDTO(userEntityRepository.save(userEntity));
    }

    public UserDTO findByEmailToken(String email){
        return findByEmail(email);
    }

    private UserEntity findUserByEmail(String email){
        return userEntityRepository
            .findOneByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The email "+email+ " was not found"));
    }

    public List<RoleDTO> getRoles(String email){
        UserEntity entity = findUserByEmail(email);
        return roleConverter.toDtoList(entity.getRoles().stream().toList());
    }

    public List<AddressDTO> getAddresses(String email){
        UserEntity entity = findUserByEmail(email);
        return addressConverter.toDtoList(entity.getAddresses());
    }

    public List<CartItemDTO> getCartItems(String email){
        UserEntity entity = findUserByEmail(email);
        return cartItemConverter.toDtoList(entity.getCartItems());
    }

    public void updateUserCart(String email, List<CartItemDTO> productIds){
        UserEntity user = findUserByEmail(email);
        user.getCartItems().forEach(item -> cartItemService.delete(item.getId()));
        // search the list of cart items
        productIds.forEach(product -> product.setUserId(user.getId()));
        // update the database
        cartItemService.saveAll( productIds );
    }

    public void delete(Long id) {
        userEntityRepository.deleteById(id);
    }

    // =============================
    //    method to get entities  //
    // =============================

}
