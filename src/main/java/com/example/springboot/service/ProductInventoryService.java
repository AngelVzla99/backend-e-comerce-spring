package com.example.springboot.service;

import com.example.springboot.converter.ProductInventoryConverter;
import com.example.springboot.dto.ProductInventoryDTO;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.ProductInventoryEntity;
import com.example.springboot.repository.ProductInventoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {
    @Autowired
    private ProductInventoryEntityRepository productInventoryRepository;
    @Autowired
    private ProductInventoryConverter productInventoryConverter;

    public Page<ProductInventoryDTO> findAllPageable(Pageable pageable) {
        Page<ProductInventoryEntity> responsePage = productInventoryRepository.findAll(pageable);
        if (!responsePage.hasContent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        return responsePage.map(productInventoryConverter::convertEntityToDto);
    }

    public ProductInventoryDTO findById(Long id) {
        Optional<ProductInventoryEntity> product = productInventoryRepository.findById(id);
        if (product.isPresent()) return productInventoryConverter.convertEntityToDto(product.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ProductInventoryDTO save(ProductInventoryDTO productEntity) {
        ProductInventoryEntity entity = productInventoryConverter.convertDtoToEntity(productEntity);
        // update the amount
        ProductEntity product = entity.getProduct();
        product.setQuantity( product.getQuantity() + entity.getQuantity() );
        // save in the database
        productInventoryRepository.save(entity);
        return productInventoryConverter.convertEntityToDto(entity);
    }

    public void delete(Long id) {
        if( productInventoryRepository.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This product inventory doesn't exist");
        productInventoryRepository.deleteById(id);
    }
}
