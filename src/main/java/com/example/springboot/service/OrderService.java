package com.example.springboot.service;


import com.example.springboot.converter.OrderConverter;
import com.example.springboot.dto.OrderDTO;
import com.example.springboot.model.OrderEntity;
import com.example.springboot.model.OrderItemEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.OrderEntityRepository;
import com.example.springboot.repository.OrderItemEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderEntityRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private OrderConverter orderConverter;

    public List<OrderDTO> getAll() {
        return orderConverter.toDtoList(orderRepository.findAll());
    }

    public Page<OrderDTO> findAllPageable(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderConverter::toDto);
    }

    public OrderDTO findById(Long id) {
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product with id "+ id +" was not found"));
        return orderConverter.toDto(order);
    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        // convert DTO to Entity and save
        OrderEntity orderEntity = orderConverter.toEntity(orderDTO);
        OrderEntity order = orderRepository.save(orderEntity);
        // save order items
        orderItemService.saveAll( orderDTO.getOrderItems(), order );
        // save order payments
        paymentMethodService.saveAll( orderDTO.getPaymentMethods(), order.getUser(), order );
        return orderConverter.toDto(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
