package com.example.springboot.service;

import com.example.springboot.converter.OrderItemConverter;
import com.example.springboot.dto.OrderItemDTO;
import com.example.springboot.model.OrderEntity;
import com.example.springboot.model.OrderItemEntity;
import com.example.springboot.repository.OrderEntityRepository;
import com.example.springboot.repository.OrderItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemEntityRepository orderItemRepository;
    @Autowired
    private OrderItemConverter orderItemConverter;

    public List<OrderItemDTO> saveAll( List<OrderItemDTO> orderItems, OrderEntity orderEntity ){
        List<OrderItemEntity> orderItemEntities = orderItemConverter
                .toEntityList( orderItems, orderEntity );
        List<OrderItemEntity> savedOrderItems = orderItemRepository.saveAll(orderItemEntities);
        return orderItemConverter.toDtoList(savedOrderItems);
    }

    public List<OrderItemEntity> getAll() {
        return orderItemRepository.findAll();
    }

    public Page<OrderItemEntity> findAllPageable(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    public Optional<OrderItemEntity> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItemEntity save(OrderItemEntity productEntity) {
        return orderItemRepository.save(productEntity);
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
