package com.example.springboot.converter;

import com.example.springboot.dto.PaymentMethodDTO;
import com.example.springboot.model.OrderEntity;
import com.example.springboot.model.PaymentMethodEntity;
import com.example.springboot.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodConverter {
    private final ModelMapper modelMapper;

    public PaymentMethodConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentMethodDTO toDto(PaymentMethodEntity paymentMethodEntity) {
        return modelMapper.map(paymentMethodEntity, PaymentMethodDTO.class);
    }

    public PaymentMethodEntity toEntity(PaymentMethodDTO paymentMethodDTO, UserEntity userEntity, OrderEntity orderEntity) {
        PaymentMethodEntity paymentM = modelMapper.map(paymentMethodDTO, PaymentMethodEntity.class);
        // relations
        paymentM.setUser(userEntity);
        paymentM.setOrder(orderEntity);
        return paymentM;
    }

    public List<PaymentMethodEntity> toEntityList(List<PaymentMethodDTO> DTOs, UserEntity user, OrderEntity order){
        return DTOs.stream().map((paymentMethodDTO)->this.toEntity(paymentMethodDTO,user,order)).toList();
    }

    public List<PaymentMethodDTO> toDtoList(List<PaymentMethodEntity> DTOs){
        return DTOs.stream().map(this::toDto).toList();
    }
}
