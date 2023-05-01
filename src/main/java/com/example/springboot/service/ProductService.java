package com.example.springboot.service;

import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductEntityRepository productRepository;

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public Page<ProductEntity> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
