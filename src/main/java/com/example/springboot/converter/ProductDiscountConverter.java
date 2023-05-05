package com.example.springboot.converter;

import com.example.springboot.dto.ProductDiscountDTO;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.ProductEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDiscountConverter {
    private final ModelMapper modelMapper;
    private final ProductEntityRepository productRepository;

    public ProductDiscountConverter(ModelMapper modelMapper, ProductEntityRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public ProductDiscountDTO toDTO(ProductDiscountEntity productDiscount) {
        ProductDiscountDTO dto = modelMapper.map(productDiscount, ProductDiscountDTO.class);
        dto.setProductIds(
                productDiscount.getProducts().stream().map(ProductEntity::getId).collect(Collectors.toList())
        );
        return dto;
    }

    public ProductDiscountEntity toEntity(ProductDiscountDTO productDiscount) {
        ProductDiscountEntity entity = modelMapper.map(productDiscount, ProductDiscountEntity.class);
        List<ProductEntity> products = productRepository
                .findByIdIn(productDiscount.getProductIds());

        // if some products are not found => 404
        List<Long> diff = new LinkedList<>(productDiscount.getProductIds()); // linkedList => hash table for removeAll
        diff.removeAll( products.stream().map(ProductEntity::getId).toList() );
        if(diff.size()>0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The products with ids "+ diff  +" was not found");

        entity.setProducts(products);
        return entity;
    }
}
