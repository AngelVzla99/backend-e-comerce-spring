package com.example.springboot.service;

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

    public List<CartItemEntity> getAll() {
        return cartItemRepository.findAll();
    }

    public Page<CartItemEntity> findAllPageable(Pageable pageable) {
        return cartItemRepository.findAll(pageable);
    }

    public Optional<CartItemEntity> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    public CartItemEntity save(CartItemEntity productEntity) {
        return cartItemRepository.save(productEntity);
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
