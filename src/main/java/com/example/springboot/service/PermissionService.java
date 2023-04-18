package com.example.springboot.service;

import com.example.springboot.model.PermissionEntity;
import com.example.springboot.repository.PermissionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermissionEntityRepository permissionRepository;

    public List<PermissionEntity> getAll() {
        return permissionRepository.findAll();
    }

    public Optional<PermissionEntity> getById(Long id) {
        return permissionRepository.findById(id);
    }

    public PermissionEntity save(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }

    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

}
