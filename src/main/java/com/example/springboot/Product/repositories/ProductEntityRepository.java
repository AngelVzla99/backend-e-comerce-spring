package com.example.springboot.Product.repositories;

import com.example.springboot.Product.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAll(Pageable pageable);
    List<ProductEntity> findByIdIn(List<Long> ids);
    Page<ProductEntity> findByNameContainingOrDescriptionContaining( String name, String description, Pageable pageable);
}