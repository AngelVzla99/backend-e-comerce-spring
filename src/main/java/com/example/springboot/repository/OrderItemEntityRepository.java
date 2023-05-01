package com.example.springboot.repository;

import com.example.springboot.model.OrderItemEntity;
import com.example.springboot.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, Long> {
    Page<OrderItemEntity> findAll(Pageable pageable);
}