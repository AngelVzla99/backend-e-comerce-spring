package com.example.springboot.repository;

import com.example.springboot.model.AddressEntity;
import com.example.springboot.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
    Page<AddressEntity> findAll(Pageable pageable);
}