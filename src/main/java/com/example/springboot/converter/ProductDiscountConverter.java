package com.example.springboot.converter;

import com.example.springboot.dto.ProductDiscountDTO;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductDiscountConverter {
    public ProductDiscountDTO toDTO(ProductDiscountEntity productDiscount) {
        return new ProductDiscountDTO(
                productDiscount.getId(),
                productDiscount.getExpireAt(),
                productDiscount.getDiscountPercentage(),
                productDiscount.getActive()
        );
    }

    public ProductDiscountEntity toEntity(ProductDiscountDTO productDiscount) {
        return new ProductDiscountEntity(
                productDiscount.getId(),
                productDiscount.getExpireAt(),
                productDiscount.getDiscountPercentage(),
                productDiscount.getActive()
        );
    }
}
