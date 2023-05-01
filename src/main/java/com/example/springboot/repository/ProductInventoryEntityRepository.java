package com.example.springboot.repository;

import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.ProductInventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryEntityRepository extends JpaRepository<ProductInventoryEntity, Long> {
    Page<ProductInventoryEntity> findAll(Pageable pageable);
}