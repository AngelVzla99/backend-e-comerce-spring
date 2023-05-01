package com.example.springboot.service;

import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.repository.ProductDiscountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDiscountService {
    @Autowired
    ProductDiscountEntityRepository productDiscountEntityRepository;

    public List<ProductDiscountEntity> getAll() {
        return productDiscountEntityRepository.findAll();
    }

    public Page<ProductDiscountEntity> findAllPageable(Pageable pageable) {
        return productDiscountEntityRepository.findAll(pageable);
    }

    public Optional<ProductDiscountEntity> findById(Long id) {
        return productDiscountEntityRepository.findById(id);
    }

    public ProductDiscountEntity save(ProductDiscountEntity user) {
        return productDiscountEntityRepository.save(user);
    }

    public void delete(Long id) {
        productDiscountEntityRepository.deleteById(id);
    }
}
