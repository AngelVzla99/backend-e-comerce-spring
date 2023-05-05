package com.example.springboot.service;

import com.example.springboot.converter.AddressConverter;
import com.example.springboot.dto.AddressDTO;
import com.example.springboot.model.AddressEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.AddressEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import com.example.springboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressEntityRepository addressEntityRepository;
    @Autowired
    private AddressConverter addressConverter;
    @Autowired
    UserEntityRepository userEntityRepository;

    public AddressDTO save(AddressDTO addressDTO) {
        AddressEntity entity = addressConverter.toEntity(addressDTO);
        return addressConverter.toDto(addressEntityRepository.save(entity));
    }

    public void delete(Long userId, Long id) {
        // find user in the db
        UserEntity user = userEntityRepository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This address don't belong to the user of the token, or not exist"));
        // search address in the db
        AddressEntity address = addressEntityRepository
                .findByUserAndId(user,id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This address don't belong to the user of the token, or not exist"));
        // delete from the db
        addressEntityRepository.deleteById(id);
    }
}
