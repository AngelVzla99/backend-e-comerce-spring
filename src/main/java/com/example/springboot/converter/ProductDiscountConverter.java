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
        return dto;
    }

    public ProductDiscountEntity toEntity(ProductDiscountDTO productDiscount) {
        ProductDiscountEntity entity = modelMapper.map(productDiscount, ProductDiscountEntity.class);
        return entity;
    }
}
