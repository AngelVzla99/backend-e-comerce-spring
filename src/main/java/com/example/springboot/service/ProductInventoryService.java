package com.example.springboot.service;

import com.example.springboot.model.ProductInventoryEntity;
import com.example.springboot.repository.ProductInventoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {
    @Autowired
    private ProductInventoryEntityRepository productInventoryRepository;

    public List<ProductInventoryEntity> getAll() {
        return productInventoryRepository.findAll();
    }

    public Page<ProductInventoryEntity> findAllPageable(Pageable pageable) {
        return productInventoryRepository.findAll(pageable);
    }

    public Optional<ProductInventoryEntity> findById(Long id) {
        return productInventoryRepository.findById(id);
    }

    public ProductInventoryEntity save(ProductInventoryEntity productEntity) {
        return productInventoryRepository.save(productEntity);
    }

    public void delete(Long id) {
        productInventoryRepository.deleteById(id);
    }
}
