package com.example.springboot.service;


import com.example.springboot.model.OrderEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.repository.OrderEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderEntityRepository orderRepository;

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public Page<OrderEntity> findAllPageable(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<OrderEntity> findById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity save(OrderEntity productEntity) {
        return orderRepository.save(productEntity);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
