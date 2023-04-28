package com.example.springboot.service;

import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleEntityRepository roleEntityRepository;

    public List<RoleEntity> getAll() {
        return roleEntityRepository.findAll();
    }

    public Page<RoleEntity> findAllPageable(Pageable pageable){ return roleEntityRepository.findAll(pageable); }

    public Optional<RoleEntity> findByRoleName(String roleName) {
        return roleEntityRepository.findOneByRoleName(roleName);
    }

    public RoleEntity findByRoleNameOrCreate(String roleName){
        Optional<RoleEntity> role = findByRoleName(roleName);
        if(role.isEmpty()){
            RoleEntity newRole = new RoleEntity();
            newRole.setRoleName(roleName);
            return save(newRole);
        }else{
            return role.get();
        }
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
