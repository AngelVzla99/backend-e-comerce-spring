package com.example.springboot.repository;

import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountEntityRepository extends JpaRepository<ProductDiscountEntity, Long> {
    Page<ProductDiscountEntity> findAll(Pageable pageable);
}