package com.example.springboot.converter;

import com.example.springboot.dto.OrderItemDTO;
import com.example.springboot.dto.PaymentMethodDTO;
import com.example.springboot.model.OrderEntity;
import com.example.springboot.model.OrderItemEntity;
import com.example.springboot.model.PaymentMethodEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.ProductEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class OrderItemConverter {
    private final ModelMapper modelMapper;
    private final ProductEntityRepository productEntityRepository;


    public OrderItemConverter(ModelMapper modelMapper, ProductEntityRepository productEntityRepository) {
        this.modelMapper = modelMapper;
        this.productEntityRepository = productEntityRepository;
    }

    public OrderItemDTO toDto(OrderItemEntity orderItemEntity) {
        return modelMapper.map(orderItemEntity, OrderItemDTO.class);
    }

    public OrderItemEntity toEntity(OrderItemDTO orderItemDTO, OrderEntity orderEntity) {
        OrderItemEntity entity = modelMapper.map(orderItemDTO, OrderItemEntity.class);
        // relations
        ProductEntity product = productEntityRepository
                .findById( orderItemDTO.getProductId() )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product with id "+orderItemDTO.getProductId() +" was not found"));
        entity.setProduct(product);
        entity.setOrder(orderEntity);
        return entity;
    }

    public List<OrderItemEntity> toEntityList(List<OrderItemDTO> DTOs, OrderEntity orderItemEntity){
        return DTOs.stream().map((orderItemDTO)->this.toEntity(orderItemDTO,orderItemEntity)).toList();
    }

    public List<OrderItemDTO> toDtoList(List<OrderItemEntity> DTOs){
        return DTOs.stream().map(this::toDto).toList();
    }
}
