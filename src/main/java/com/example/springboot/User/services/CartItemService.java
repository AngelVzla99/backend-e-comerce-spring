package com.example.springboot.User.services;

import com.example.springboot.User.converter.CartItemConverter;
import com.example.springboot.User.dto.CartItemDTO;
import com.example.springboot.User.entities.CartItemEntity;
import com.example.springboot.User.repositories.CartItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartItemService {
    @Autowired
    private CartItemEntityRepository cartItemRepository;
    @Autowired
    private CartItemConverter cartItemConverter;

    public List<CartItemDTO> saveAll(List<CartItemDTO> cartItemDTOs) {
        List<CartItemEntity> entities = cartItemConverter.toEntityList(cartItemDTOs);
        return cartItemConverter.toDtoList( cartItemRepository.saveAll(entities) );
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
