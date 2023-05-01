package com.example.springboot.service;

import com.example.springboot.model.OrderItemEntity;
import com.example.springboot.model.PaymentMethodEntity;
import com.example.springboot.repository.PaymentMethodEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodEntityRepository paymentMethodRepository;

    public List<PaymentMethodEntity> getAll() {
        return paymentMethodRepository.findAll();
    }

    public Page<PaymentMethodEntity> findAllPageable(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    public Optional<PaymentMethodEntity> findById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    public PaymentMethodEntity save(PaymentMethodEntity productEntity) {
        return paymentMethodRepository.save(productEntity);
    }

    public void delete(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
