package com.example.springboot.service;

import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleEntityRepository roleEntityRepository;

    public List<RoleEntity> getAll() {
        return roleEntityRepository.findAll();
    }

    public Optional<RoleEntity> getById(Long id) {
        return roleEntityRepository.findById(id);
    }

    public RoleEntity save(RoleEntity user) {
        return roleEntityRepository.save(user);
    }

    public void delete(Long id) {
        roleEntityRepository.deleteById(id);
    }
}
