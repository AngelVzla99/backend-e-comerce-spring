package com.example.springboot.converter;

import com.example.springboot.dto.ProductDTO;
import com.example.springboot.model.CategoryEntity;
import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.CategoryEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    ProductEntityRepository productRepository;
    CategoryEntityRepository categoryEntityRepository;

    public ProductConverter(ProductEntityRepository productRepository, CategoryEntityRepository categoryEntityRepository) {
        this.productRepository = productRepository;
        this.categoryEntityRepository = categoryEntityRepository;
    }

    public ProductDTO toDTO(ProductEntity product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());

        ProductDiscountEntity productDiscount = product.getProductDiscount();
        if( productDiscount != null ) dto.setDiscountId(productDiscount.getId());

        dto.setCategories(
                product
                        .getCategories()
                        .stream()
                        .map(p->p.getId())
                        .collect(Collectors.toList())
        );

        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setTaxPercentage(product.getTaxPercentage());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescription());
        dto.setPhotoUrlSmall(product.getPhotoUrlSmall());
        dto.setPhotoUrlMedium(product.getPhotoUrlMedium());
        dto.setPhotoUrlBig(product.getPhotoUrlBig());
        dto.setAmount(product.getAmount());
        dto.setWeight(product.getWeight());
        dto.setHeight(product.getHeight());
        return dto;
    }

    public ProductEntity toEntity(ProductDTO dto, ProductDiscountEntity discount) {
        ProductEntity product = new ProductEntity();
        product.setId(dto.getId());
        product.setProductDiscount(discount);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setTaxPercentage(dto.getTaxPercentage());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        product.setPhotoUrlSmall(dto.getPhotoUrlSmall());
        product.setPhotoUrlMedium(dto.getPhotoUrlMedium());
        product.setPhotoUrlBig(dto.getPhotoUrlBig());
        product.setAmount(dto.getAmount());
        product.setWeight(dto.getWeight());
        product.setHeight(dto.getHeight());
        // categories
        Set<CategoryEntity> categories = new HashSet<>();
        dto.getCategories().forEach(
                cId->{
                    CategoryEntity cEntity = categoryEntityRepository
                            .findById(cId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category "+cId+" isn't in the database or isn't a leaf"));
                    categories.add(cEntity);
                }
        );
        product.setCategories(categories);
        return product;
    }
}

