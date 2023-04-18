package com.example.springboot.service;

import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleEntityRepository roleEntityRepository;

    public List<RoleEntity> getAll() {
        return roleEntityRepository.findAll();
    }

    public RoleEntity getById(Long id) {
        return roleEntityRepository.findById(id).get();
    }

    public RoleEntity save(RoleEntity user) {
        return roleEntityRepository.save(user);
    }

    public void delete(Long id) {
        roleEntityRepository.deleteById(id);
    }
}
