package com.example.springboot.service;

import com.example.springboot.converter.ProductConverter;
import com.example.springboot.dto.ProductDTO;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductEntityRepository productRepository;
    @Autowired
    private ProductConverter productConverter;

    public Page<ProductDTO> findAllPageable(Pageable pageable) {
        Page<ProductEntity> responsePage = productRepository.findAll(pageable);
        if (!responsePage.hasContent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        return responsePage.map(productConverter::toDTO);
    }

    public ProductDTO findById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) return productConverter.toDTO(product.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.toEntity(productDTO, null);
        ProductEntity product = productRepository.save(productEntity);
        // add product to the category set
        productEntity.getCategories().forEach(categoryEntity -> categoryEntity.addProduct(product) );
        // save product in the database
        return productConverter.toDTO(product);
    }

    public void delete(Long id) {
        if( productRepository.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This discount doesn't exist");
        productRepository.deleteById(id);
    }
}
