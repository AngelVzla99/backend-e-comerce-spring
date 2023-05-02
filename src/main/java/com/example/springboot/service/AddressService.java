package com.example.springboot.service;

import com.example.springboot.model.AddressEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.AddressEntityRepository;
import com.example.springboot.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressEntityRepository addressEntityRepository;

    public List<AddressEntity> getAll() {
        return addressEntityRepository.findAll();
    }

    public Page<AddressEntity> findAllPageable(Pageable pageable) {
        return addressEntityRepository.findAll(pageable);
    }

    public Optional<AddressEntity> findById(Long id) {
        return addressEntityRepository.findById(id);
    }

    public Optional<AddressEntity> findByUserAndId(UserEntity user, Long id){
        return addressEntityRepository.findByUserAndId(user,id);
    }

    public AddressEntity save(AddressEntity productEntity) {
        return addressEntityRepository.save(productEntity);
    }

    public void delete(Long id) {
        addressEntityRepository.deleteById(id);
    }
}
