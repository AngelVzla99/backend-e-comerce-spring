package com.example.springboot.service;

import com.example.springboot.model.CategoryEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.CategoryEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryEntityRepository categoryRepository;

    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public Page<CategoryEntity> findAllPageable(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntity save(CategoryEntity productEntity) {
        return categoryRepository.save(productEntity);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
