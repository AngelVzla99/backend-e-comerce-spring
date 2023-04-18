package com.example.springboot.repository;

import com.example.springboot.model.PermissionEntity;
import com.example.springboot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findByEmail(String email);

}