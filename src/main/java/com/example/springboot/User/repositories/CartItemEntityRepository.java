package com.example.springboot.User.repositories;

import com.example.springboot.User.entities.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Long> {
    Page<CartItemEntity> findAll(Pageable pageable);
}