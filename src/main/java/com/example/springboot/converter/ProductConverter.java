package com.example.springboot.converter;

import com.example.springboot.dto.ProductDTO;
import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDTO toDTO(ProductEntity product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());

        ProductDiscountEntity productDiscount = product.getProductDiscount();
        if( productDiscount != null ) dto.setDiscountId(productDiscount.getId());
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
        return product;
    }
}

