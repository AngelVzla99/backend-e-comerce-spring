package com.example.springboot.repository;

import com.example.springboot.model.CartItemEntity;
import com.example.springboot.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Long> {
    Page<CartItemEntity> findAll(Pageable pageable);
}