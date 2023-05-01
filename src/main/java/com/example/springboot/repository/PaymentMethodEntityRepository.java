package com.example.springboot.repository;

import com.example.springboot.model.PaymentMethodEntity;
import com.example.springboot.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodEntityRepository extends JpaRepository<PaymentMethodEntity, Long> {
    Page<PaymentMethodEntity> findAll(Pageable pageable);
}