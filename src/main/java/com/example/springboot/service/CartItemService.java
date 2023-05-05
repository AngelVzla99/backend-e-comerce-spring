package com.example.springboot.service;

import com.example.springboot.converter.CartItemConverter;
import com.example.springboot.dto.CartItemDTO;
import com.example.springboot.model.CartItemEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.CartItemEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemEntityRepository cartItemRepository;
    @Autowired
    private CartItemConverter cartItemConverter;

    public List<CartItemDTO> saveAll(List<CartItemDTO> cartItemEntities) {
        List<CartItemEntity> entities = cartItemConverter.toEntityList(cartItemEntities);
        return cartItemConverter.toDtoList( cartItemRepository.saveAll(entities) );
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
