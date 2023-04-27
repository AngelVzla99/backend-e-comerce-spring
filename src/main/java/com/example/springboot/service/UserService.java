package com.example.springboot.service;

import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    public List<UserEntity> getAll() {
        return userEntityRepository.findAll();
    }

    public Optional<UserEntity> getById(Long id) {
        return userEntityRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userEntityRepository.save(user);
    }

    public void delete(Long id) {
        userEntityRepository.deleteById(id);
    }
}
