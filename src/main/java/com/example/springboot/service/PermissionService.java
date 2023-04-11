package com.example.springboot.service;

import com.example.springboot.model.PermissionEntity;
import com.example.springboot.repository.PermissionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionEntityRepository permissionRepository;

    public List<PermissionEntity> getPermissions(){
        return permissionRepository.findAll();
    }
}
