package com.example.springboot.service;

import com.example.springboot.converter.CartItemConverter;
import com.example.springboot.dto.CartItemDTO;
import com.example.springboot.dto.OrderItemDTO;
import com.example.springboot.model.CartItemEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.CartItemEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

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
