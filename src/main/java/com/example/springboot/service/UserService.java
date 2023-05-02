package com.example.springboot.service;

import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Optional<UserEntity>findByEmail(String email){ return  userEntityRepository.findOneByEmail(email); }

    public UserEntity findByEmailToken(String email){
        return findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The email of your token is not in the database"));
    }

    public void delete(Long id) {
        userEntityRepository.deleteById(id);
    }
}
